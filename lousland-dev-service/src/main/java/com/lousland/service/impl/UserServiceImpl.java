package com.lousland.service.impl;

import com.lousland.mapper.UsersMapper;
import com.lousland.pojo.Users;
import com.lousland.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author ：Hanzhi
 * @description：TODO
 * @date ：2021/3/19 14:46
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return boolean true-存在；false-不存在
     */
    @Override
    public boolean queryUsernameIsExist(String username) {

        Example userExample = new Example(Users.class);
        // 创建条件
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        // Mapper
        Users isExist = usersMapper.selectOneByExample(userExample);
        return isExist != null;
    }
}
