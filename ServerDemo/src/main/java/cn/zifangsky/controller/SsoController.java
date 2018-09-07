package cn.zifangsky.controller;

import cn.zifangsky.common.Constants;
import cn.zifangsky.common.SpringContextUtils;
import cn.zifangsky.enums.ErrorCodeEnum;
import cn.zifangsky.enums.ExpireEnum;
import cn.zifangsky.model.SsoAccessToken;
import cn.zifangsky.model.SsoClientDetails;
import cn.zifangsky.model.SsoRefreshToken;
import cn.zifangsky.model.User;
import cn.zifangsky.model.bo.UserBo;
import cn.zifangsky.service.SsoService;
import cn.zifangsky.service.UserService;
import cn.zifangsky.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * SSO单点登录相关接口
 *
 * @author zifangsky
 * @date 2018/8/30
 * @since 1.0.0
 */
@Controller
@RequestMapping("/sso")
public class SsoController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "ssoServiceImpl")
    private SsoService ssoService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 获取Token
     * @author zifangsky
     * @date 2018/8/30 16:30
     * @since 1.0.0
     * @param request HttpServletRequest
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/token")
    public ModelAndView authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        //过期时间
        Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
        //回调URL
        String redirectUri = request.getParameter("redirect_uri");
        //查询接入客户端
        SsoClientDetails ssoClientDetails = ssoService.selectByRedirectUrl(redirectUri);

        //获取用户IP
        String requestIp = SpringContextUtils.getRequestIp(request);

        //生成Access Token
        String accessTokenStr = ssoService.createAccessToken(user, expiresIn, requestIp, ssoClientDetails);
        //查询已经插入到数据库的Access Token
        SsoAccessToken ssoAccessToken = ssoService.selectByAccessToken(accessTokenStr);
        //生成Refresh Token
        String refreshTokenStr = ssoService.createRefreshToken(user, ssoAccessToken);
        logger.info(MessageFormat.format("单点登录获取Token：username:【{0}】,channel:【{1}】,Access Token:【{2}】,Refresh Token:【{3}】"
                ,user.getUsername(),ssoClientDetails.getClientName(),accessTokenStr,refreshTokenStr));

        String params = "?code=" + accessTokenStr;
        return new ModelAndView("redirect:" + redirectUri + params);
    }

    /**
     * 校验Access Token，并返回用户信息
     * @author zifangsky
     * @date 2018/8/30 16:07
     * @since 1.0.0
     * @param request HttpServletRequest
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String,Object> verify(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(8);

        //获取Access Token
        String accessToken = request.getParameter("access_token");

        try {
            //过期时间
            Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
            //查询Access Token
            SsoAccessToken ssoAccessToken = ssoService.selectByAccessToken(accessToken);
            //查询Refresh Token
            SsoRefreshToken ssoRefreshToken = ssoService.selectByTokenId(ssoAccessToken.getId());
            //查询用户信息
            UserBo userBo = userService.selectUserBoByUserId(ssoAccessToken.getUserId());

            //组装返回信息
            result.put("access_token", ssoAccessToken.getAccessToken());
            result.put("refresh_token", ssoRefreshToken.getRefreshToken());
            result.put("expires_in", expiresIn);
            result.put("user_info", userBo);
            return result;
        }catch (Exception e){
            logger.error(e.getMessage());
            this.generateErrorResponse(result, ErrorCodeEnum.UNKNOWN_ERROR);
            return result;
        }
    }

    /**
     * 通过Refresh Token刷新Access Token
     * @author zifangsky
     * @date 2018/8/30 16:07
     * @since 1.0.0
     * @param request HttpServletRequest
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @RequestMapping(value = "/refreshToken", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String,Object> refreshToken(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>(8);

        //获取Refresh Token
        String refreshTokenStr = request.getParameter("refresh_token");
        //获取用户IP
        String requestIp = SpringContextUtils.getRequestIp(request);

        try {
            SsoRefreshToken ssoRefreshToken = ssoService.selectByRefreshToken(refreshTokenStr);

            if(ssoRefreshToken != null) {
                Long savedExpiresAt = ssoRefreshToken.getExpiresIn();
                //过期日期
                LocalDateTime expiresDateTime = DateUtils.ofEpochSecond(savedExpiresAt, null);
                //当前日期
                LocalDateTime nowDateTime = DateUtils.now();

                //如果Refresh Token已经失效，则需要重新生成
                if (expiresDateTime.isBefore(nowDateTime)) {
                    this.generateErrorResponse(result, ErrorCodeEnum.EXPIRED_TOKEN);
                    return result;
                } else {
                    //获取存储的Access Token
                    SsoAccessToken ssoAccessToken = ssoService.selectByAccessId(ssoRefreshToken.getTokenId());
                    //查询接入客户端
                    SsoClientDetails ssoClientDetails = ssoService.selectByPrimaryKey(ssoAccessToken.getClientId());
                    //获取对应的用户信息
                    User user = userService.selectByUserId(ssoAccessToken.getUserId());

                    //新的过期时间
                    Long expiresIn = DateUtils.dayToSecond(ExpireEnum.ACCESS_TOKEN.getTime());
                    //生成新的Access Token
                    String newAccessTokenStr = ssoService.createAccessToken(user, expiresIn, requestIp, ssoClientDetails);
                    //查询用户信息
                    UserBo userBo = userService.selectUserBoByUserId(ssoAccessToken.getUserId());

                    logger.info(MessageFormat.format("单点登录重新刷新Token：username:【{0}】,requestIp:【{1}】,old token:【{2}】,new token:【{3}】"
                            ,user.getUsername(),requestIp,ssoAccessToken.getAccessToken(),newAccessTokenStr));

                    //组装返回信息
                    result.put("access_token", newAccessTokenStr);
                    result.put("refresh_token", ssoRefreshToken.getRefreshToken());
                    result.put("expires_in", expiresIn);
                    result.put("user_info", userBo);
                    return result;
                }
            }else {
                this.generateErrorResponse(result, ErrorCodeEnum.INVALID_GRANT);
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            this.generateErrorResponse(result, ErrorCodeEnum.UNKNOWN_ERROR);
            return result;
        }
    }

    /**
     * 组装错误请求的返回
     */
    private void generateErrorResponse(Map<String,Object> result, ErrorCodeEnum errorCodeEnum) {
        result.put("error", errorCodeEnum.getError());
        result.put("error_description",errorCodeEnum.getErrorDescription());
    }

}
