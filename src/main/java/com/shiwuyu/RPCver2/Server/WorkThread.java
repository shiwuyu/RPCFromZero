package com.shiwuyu.RPCver2.Server;

import com.shiwuyu.RPCver2.Common.RPCRequest;
import com.shiwuyu.RPCver2.Common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 工作服务类,从服务端代码分离出来,简化服务端代码,单一职责原则
 * 这里负责解析得到的request请求,执行服务方法,返回给客户端;
 * 1.从request得到InterfaceName
 * 2.根据interfaceName在serviceProvider中找到服务端的实现类
 * 3.从request中得到方法名,参数,利用反射执行服务中的方法
 * 4.得到结果,封装成response,写入socket
 *
 * @author 张非凡
 * @version 1.0.0
 * @date 2023-12-16 13:12
 */
@AllArgsConstructor
public class WorkThread implements Runnable {

    private Socket socket;
    private ServiceProvider serviceProvider;

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            // 读取客户端传过来的数据
            RPCRequest request = (RPCRequest) ois.readObject();
            // 反射调用服务方法获得返回值
            RPCResponse response = getResponse(request);
            // 写入客户端
            oos.writeObject(response);
            oos.flush();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("从IO中读取数据错误!");
        }
    }

    public RPCResponse getResponse(RPCRequest request) {
        // 得到服务名
        String interfaceName = request.getInterfaceName();
        // 得到服务端实现类
        Object service = serviceProvider.getService(interfaceName);
        // 反射调用方法
        Method method = null;
        try {
            method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行错误!");
            return RPCResponse.fail();
        }
    }
}
