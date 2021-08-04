package com.lousland.service.impl;

import com.lousland.enums.Sex;
import com.lousland.mapper.UsersMapper;
import com.lousland.pojo.Users;
import com.lousland.pojo.bo.UserBo;
import com.lousland.service.UserService;
import com.lousland.utils.DateUtil;
import com.lousland.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * 用户服务类
 * @author ：Hanzhi
 * @date ：2021/3/19 14:46
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private Sid sid;

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return boolean true-存在；false-不存在
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = RuntimeException.class)
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

    /**
     * 创建用户
     * @param userBo
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users createUser(UserBo userBo) {

        Assert.isTrue(userBo != null,"createUser error!");
        // 随机16位字符串
        String userId = sid.nextShort();

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBo.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBo.getPassword()));
        } catch (Exception e) {
            logger.error("createUser : ", e);
        }
        // 默认昵称为用户名
        user.setNickname(userBo.getUsername());
        user.setSex(Sex.secret.type);
        user.setBirthday(DateUtil.stringToDate("1994-6-14"));
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }
}
