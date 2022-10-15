package com.shiwuyu.RPCver0.Server;

import com.shiwuyu.RPCver0.Common.User;
import com.shiwuyu.RPCver0.Service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @Program: RPCFromZero
 * @Description: 服务端需要实现Service接口的功能
 * @Author: 张非凡
 * @Create: 2022-10-15 23:19
 **/
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(Integer id) {
        System.out.println("This Client has select a user which id is "+id);
        //模拟从数据库里去用户的行为
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }
}
