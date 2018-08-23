package cn.zifangsky.interceptor;

import cn.zifangsky.common.Constants;
import cn.zifangsky.common.SpringContextUtils;
import cn.zifangsky.enums.ErrorCodeEnum;
import cn.zifangsky.mapper.AuthClientDetailsMapper;
import cn.zifangsky.mapper.AuthClientUserMapper;
import cn.zifangsky.mapper.AuthScopeMapper;
import cn.zifangsky.model.AuthClientDetails;
import cn.zifangsky.model.AuthClientUser;
import cn.zifangsky.model.AuthScope;
import cn.zifangsky.model.User;
import cn.zifangsky.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 检查是否已经存在授权
 *
 * @author zifangsky
 * @date 2018/8/10
 * @since 1.0.0
 */
public class OauthInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private AuthClientUserMapper authClientUserMapper;
    @Autowired
    private AuthClientDetailsMapper authClientDetailsMapper;
    @Autowired
    private AuthScopeMapper authScopeMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //参数信息
        String params = "?redirectUri=" + SpringContextUtils.getRequestUrl(request);

        //客户端ID
        String clientIdStr = request.getParameter("client_id");
        //权限范围
        String scopeStr = request.getParameter("scope");
        //回调URL
        String redirectUri = request.getParameter("redirect_uri");
        //返回形式
        String responseType = request.getParameter("response_type");

        //获取session中存储的token
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        if(StringUtils.isNoneBlank(clientIdStr) && StringUtils.isNoneBlank(scopeStr) && StringUtils.isNoneBlank(redirectUri) && "code".equals(responseType)){
            params = params + "&client_id=" + clientIdStr + "&scope=" + scopeStr;

            //1. 查询是否存在授权信息
            AuthClientDetails clientDetails = authClientDetailsMapper.selectByClientId(clientIdStr);
            AuthScope scope = authScopeMapper.selectByScopeName(scopeStr);

            if(clientDetails == null){
                return this.generateErrorResponse(response, ErrorCodeEnum.INVALID_CLIENT);
            }

            if(scope == null){
                return this.generateErrorResponse(response, ErrorCodeEnum.INVALID_SCOPE);
            }

            if(!clientDetails.getRedirectUri().equals(redirectUri)){
                return this.generateErrorResponse(response, ErrorCodeEnum.REDIRECT_URI_MISMATCH);
            }

            //2. 查询用户给接入的客户端是否已经授权
            AuthClientUser clientUser = authClientUserMapper.selectByClientId(clientDetails.getId(), user.getId(), scope.getId());
            if(clientUser != null){
                return true;
            }else{
                //如果没有授权，则跳转到授权页面
                response.sendRedirect(request.getContextPath() + "/oauth2.0/authorizePage" + params);
                return false;
            }
        }else{
            return this.generateErrorResponse(response, ErrorCodeEnum.INVALID_REQUEST);
        }
    }
    
    /**
     * 组装错误请求的返回
     */
    private boolean generateErrorResponse(HttpServletResponse response,ErrorCodeEnum errorCodeEnum) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        Map<String,String> result = new HashMap<>(2);
        result.put("error", errorCodeEnum.getError());
        result.put("error_description",errorCodeEnum.getErrorDescription());

        response.getWriter().write(JsonUtils.toJson(result));
        return false;
    }

}
