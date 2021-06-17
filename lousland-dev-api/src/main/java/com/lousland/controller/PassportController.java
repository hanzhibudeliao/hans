package com.lousland.controller;

import com.lousland.pojo.Users;
import com.lousland.pojo.bo.UserBo;
import com.lousland.service.UserService;
import com.lousland.utils.AjaxJson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：Hanzhi
 * @date ：2021/3/19 14:38
 */
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public AjaxJson usernameIsExist(@RequestParam String username) {

        if (StringUtils.isBlank(username)) {
            return AjaxJson.errorMsg("username cannot be blank!");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        // true - exist ; false - not exist
        if (isExist) {
            return AjaxJson.errorMsg("username already exist!");
        }
        return AjaxJson.ok("Congrats! username can be used.", null);
    }

    @PostMapping("/register")
    public AjaxJson register(@RequestBody UserBo userBo) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();

        // 1.校验用户名和密码是否为空
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPassword)) {
            return AjaxJson.errorMsg("username or password cannot be blank!");
        }
        // 2.查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return AjaxJson.errorMsg("username already exist!");
        }
        // 3.判断密码长度
        if (password.length() < 6) {
            return AjaxJson.errorMsg("Make sure your password at least 6 characters");
        }
        // 4.判断两次密码是否相等
        if (!password.equals(confirmPassword)) {
            return AjaxJson.errorMsg("Two passwords your entering must be the same");
        }
        // 5.实现注册
        Users user = userService.createUser(userBo);
        if (user != null) {
            return AjaxJson.ok("regist success!", user);
        }
        return AjaxJson.errorMsg("register fail");
    }
}
