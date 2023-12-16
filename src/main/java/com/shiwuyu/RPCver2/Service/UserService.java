package com.shiwuyu.RPCver2.Service;

import com.shiwuyu.RPCver2.Common.User;

public interface UserService {
    // 客户端通过这个接口调用服务端的实现类
    User getUserByUserId(Integer id);
    // 给这个服务端增加一个功能
    Integer insertUserId(User user);
}
