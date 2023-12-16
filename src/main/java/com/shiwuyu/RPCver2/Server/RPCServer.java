package com.shiwuyu.RPCver2.Server;

// 把RPCServer抽象成接口
public interface RPCServer {
    void start(int port);
    void stop();
}
