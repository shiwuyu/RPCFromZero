package com.shiwuyu.RPCver2.Server;

import com.shiwuyu.RPCver2.Server.WorkThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * RPCServie简单版本的实现,Java原始的BIO监听模式,来一个任务,就new 一个线程去处理
 * 处理任务的工作见WorkThread
 *
 * @author 张非凡
 * @version 1.0.0
 * @date 2023-12-05 16:23
 */
public class SimpleRPCServer implements RPCServer{
    // 存着服务接口名 -> service对象
    private ServiceProvider serviceProvider;

    public SimpleRPCServer(ServiceProvider serviceProvide) {
        this.serviceProvider = serviceProvide;
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动了");
            //原始的BIO方式监听
            while (true) {
                Socket socket = serverSocket.accept();
                // 开一个新线程去处理
                new Thread(new WorkThread(socket, serviceProvider)).start();;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }
    }

    @Override
    public void stop() {
    }
}
