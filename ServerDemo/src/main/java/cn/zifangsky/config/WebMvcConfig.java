package cn.zifangsky.config;

import cn.zifangsky.interceptor.AuthAccessTokenInterceptor;
import cn.zifangsky.interceptor.LoginInterceptor;
import cn.zifangsky.interceptor.OauthInterceptor;
import cn.zifangsky.interceptor.SsoAccessDomainInterceptor;
import cn.zifangsky.interceptor.SsoAccessTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web相关配置
 * @author zifangsky
 * @date 2018/7/9
 * @since 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 视图控制器
     */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**","/oauth2.0/authorizePage","/oauth2.0/authorize","/sso/token");
		registry.addInterceptor(oauthInterceptor()).addPathPatterns("/oauth2.0/authorize");
		registry.addInterceptor(accessTokenInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(ssoAccessDomainInterceptor()).addPathPatterns("/sso/token");
		registry.addInterceptor(ssoAccessTokenInterceptor()).addPathPatterns("/sso/verify");
	}

	@Bean
	public OauthInterceptor oauthInterceptor(){
		return new OauthInterceptor();
	}

	@Bean
	public AuthAccessTokenInterceptor accessTokenInterceptor(){
	    return new AuthAccessTokenInterceptor();
    }

    @Bean
    public SsoAccessTokenInterceptor ssoAccessTokenInterceptor(){
	    return new SsoAccessTokenInterceptor();
    }

    @Bean
    public SsoAccessDomainInterceptor ssoAccessDomainInterceptor(){
	    return  new SsoAccessDomainInterceptor();
    }

}
