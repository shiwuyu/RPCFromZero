package com.shiwuyu.RPCver1.Server;

import com.shiwuyu.RPCver1.Common.RPCRequest;
import com.shiwuyu.RPCver1.Common.RPCResponse;
import com.shiwuyu.RPCver1.Common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Program: RPCFromZero
 * @Description: 服务端以BIO的方式监听Socket
 * @Author: 张非凡
 * @Create: 2022-10-15 23:38
 **/
public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("The Server has started.");
            // BIO的方式监听Socket
            while (true){
                Socket socket = serverSocket.accept();
                // 开启一个线程去处理
                new Thread(()->{
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        // 读取客户端传过来的id
                        RPCRequest request = (RPCRequest) ois.readObject();
                        // 反射调用对应方法,为什么是userService.getClass()呢?是因为要调用的方法必然是在userService中
                        // 也就是我们在userService中找这个方法
                        Method method = userService.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
                        Object invoke = method.invoke(userService, request.getParams());
                        // 封装，写入response对象
                        oos.writeObject(RPCResponse.success(invoke));
                        oos.flush();
                    } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
                        e.printStackTrace();
                        System.out.println("从IO中读取数据错误");
                    }
                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("The Client fail start.");
        }
    }
}

