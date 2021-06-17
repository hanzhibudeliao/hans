package com.lousland.service;

import com.lousland.pojo.Users;
import com.lousland.pojo.bo.UserBo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return boolean true-存在；false-不存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBo
     * @return
     */
    Users createUser(@RequestBody UserBo userBo);

}
