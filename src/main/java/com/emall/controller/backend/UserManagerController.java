package com.emall.controller.backend;

import com.emall.commom.Const;
import com.emall.commom.ServerResponse;
import com.emall.pojo.User;
import com.emall.service.IUserService;
import com.emall.utils.CookieUtil;
import com.emall.utils.JsonUtil;
import com.emall.utils.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class UserManagerController {
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String userName, String password, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,HttpSession session) {
        ServerResponse<User> response = userService.login(userName, password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (user.getRole() == (Const.Role.ROLE_ADMIN)) {
                CookieUtil.writeLoginToken(httpServletResponse, session.getId());
                RedisPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
                return response;
            }
            return ServerResponse.createByErroMessage("不是管理员无法登陆");
        }
        return response;
    }
}
