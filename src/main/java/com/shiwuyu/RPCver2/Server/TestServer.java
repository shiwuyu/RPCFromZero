package com.shiwuyu.RPCver2.Server;

import com.shiwuyu.RPCver2.Server.UserServiceImpl;
import com.shiwuyu.RPCver2.Service.UserService;
import com.shiwuyu.RPCver2.Service.BlogService;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 *
 * @author 张非凡
 * @version 1.0.0
 * @date 2023-12-05 16:04
 */
public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
//        Map<String, Object> serviceProvide = new HashMap<>();
//        // 暴露两个服务接口, 即在RPCServer中加一个HashMap
//        serviceProvide.put("com.shiwuyu.RPCver2.service.UserService", userService);
//        serviceProvide.put("com.shiwuyu.RPCver2.service.BlogService", userService);

        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer rpcServer = new SimpleRPCServer(serviceProvider);
        rpcServer.start(8888);
    }
}
