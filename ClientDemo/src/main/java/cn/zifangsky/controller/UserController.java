package cn.zifangsky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户相关controller
 *
 * @author zifangsky
 * @date 2018/7/9
 * @since 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 用户首页
     * @author zifangsky
     * @date 2018/7/9 17:10
     * @since 1.0.0
     * @return java.lang.String
     */
    @RequestMapping("/userIndex")
    public String userIndex(){
        return "userIndex";
    }

    /**
     * 一个测试的受保护的页面
     * @author zifangsky
     * @date 2018/7/9 17:10
     * @since 1.0.0
     * @return java.lang.String
     */
    @RequestMapping("/protected")
    public String protectedPage(){
        return "protected";
    }
}
