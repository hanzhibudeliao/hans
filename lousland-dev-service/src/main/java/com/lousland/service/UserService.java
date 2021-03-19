package com.lousland.service;

import org.springframework.stereotype.Service;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return boolean true-存在；false-不存在
     */
    public boolean queryUsernameIsExist(String username);

}
