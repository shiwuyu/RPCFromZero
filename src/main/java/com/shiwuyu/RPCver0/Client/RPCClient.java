package com.shiwuyu.RPCver0.Client;

import com.shiwuyu.RPCver0.Common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * @Program: RPCFromZero
 * @Description: 客户端建立Socket连接, 传输id给服务端, 得到返回的User对象
 * @Author: 张非凡
 * @Create: 2022-10-15 23:27
 **/
public class RPCClient {
    public static void main(String[] args) {
        try{
            //建立客户端链接
            Socket socket = new Socket("127.0.0.1", 8888);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //传给服务器Id
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();
            // 服务器查询,返回对应对象
            User user = (User) objectInputStream.readObject();
            System.out.println("The Sever has return the user id :" + user.getId());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("The Client has started fail.");
        }
    }
}
