package cn.zifangsky.controller;

import cn.zifangsky.common.Constants;
import cn.zifangsky.model.SsoResponse;
import cn.zifangsky.model.User;
import cn.zifangsky.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录
 * @author zifangsky
 * @date 2018/7/9
 * @since 1.0.0
 */
@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${own.sso.access-token-uri}")
    private String accessTokenUri;

    @Value("${own.sso.verify-uri}")
    private String verifyUri;

    /**
     * 登录验证（实际登录调用认证服务器）
     * @author zifangsky
     * @date 2018/8/30 18:02
     * @since 1.0.0
     * @param request HttpServletRequest
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        //当前系统登录成功之后的回调URL
        String redirectUrl = request.getParameter("redirectUrl");
        //当前系统请求认证服务器成功之后返回的Token
        String code = request.getParameter("code");

        //最后重定向的URL
        String resultUrl = "redirect:";
        HttpSession session = request.getSession();

        //1. code为空，则说明当前请求不是认证服务器的回调请求，则重定向URL到认证服务器登录
        if(StringUtils.isBlank(code)){
            //如果存在回调URL，则将这个URL添加到session
            if(StringUtils.isNoneBlank(redirectUrl)){
                session.setAttribute(Constants.SESSION_LOGIN_REDIRECT_URL,redirectUrl);
            }

            //拼装请求Token的地址
            resultUrl += accessTokenUri;
        }else{
            //2. 验证Token，并返回用户基本信息、所属角色、访问权限等
            SsoResponse verifyResponse = restTemplate.getForObject(verifyUri, SsoResponse.class
                    ,code);

            //如果正常返回
            if(StringUtils.isNoneBlank(verifyResponse.getAccess_token())){
                //2.1 将用户信息存到session
                session.setAttribute(Constants.SESSION_USER,verifyResponse.getUser_info());

                //2.2 将Access Token和Refresh Token写到cookie
                CookieUtils.addCookie(response,Constants.COOKIE_ACCESS_TOKEN, verifyResponse.getAccess_token(),request.getServerName());
                CookieUtils.addCookie(response,Constants.COOKIE_REFRESH_TOKEN, verifyResponse.getRefresh_token(),request.getServerName());
            }

            //3. 从session中获取回调URL，并返回
            redirectUrl = (String) session.getAttribute(Constants.SESSION_LOGIN_REDIRECT_URL);
            session.removeAttribute("redirectUrl");
            if(StringUtils.isNoneBlank(redirectUrl)){
                resultUrl += redirectUrl;
            }else{
                resultUrl += "/user/userIndex";
            }
        }

        return new ModelAndView(resultUrl);
    }

}
