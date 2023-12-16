package com.shiwuyu.RPCver2.Server;

import java.util.HashMap;
import java.util.Map;

/**
 * 之前使用Map简单实现存放服务接口名与服务端对应的实现类
 * 服务启动需要暴露其相关的实现类0,根据request中的interface调用服务端中相关实现类
 *
 * @author 张非凡
 * @version 1.0.0
 * @date 2023-12-16 12:56
 */
public class ServiceProvider {
    /*
    * 一个类可能实现多个接口
    */
    private Map<String, Object> interfaceProvider;

    public ServiceProvider() {
        this.interfaceProvider = new HashMap<>();
    }

    public void provideServiceInterface(Object service) {
        String serviceName = service.getClass().getName();
        Class<?>[] interfaces = service.getClass().getInterfaces();

        for (Class clazz : interfaces) {
            interfaceProvider.put(clazz.getName(), service);
        }
    }

    public Object getService(String interfaceName) {
        return interfaceProvider.get(interfaceName);
    }
}
