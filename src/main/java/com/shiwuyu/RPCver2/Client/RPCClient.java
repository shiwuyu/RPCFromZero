package com.shiwuyu.RPCver2.Client;

import com.shiwuyu.RPCver2.Common.Blog;
import com.shiwuyu.RPCver2.Common.User;
import com.shiwuyu.RPCver2.Service.BlogService;
import com.shiwuyu.RPCver2.Service.UserService;


/**
 * @Program: RPCFromZero
 * @Description: 客户端建立Socket连接, 传输id给服务端, 得到返回的User对象
 * @Author: 张非凡
 * @Create: 2022-10-15 23:27
 **/
public class RPCClient {
    public static void main(String[] args) {
        /*try{
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
        }*/

        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8888);
        UserService proxy = clientProxy.getProxy(UserService.class);

        // 服务方法1
        User userById = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为:" + userById);
        // 服务方法2
        User user = User.builder().userName("张三").sex(true).id(100).build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("向服务器插入数据:"+ integer);

        // 新的测试用例
        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blogById = blogService.getBlogById(10000);
        System.out.println("从服务端得到的blog为" + blogById);
    }
}
