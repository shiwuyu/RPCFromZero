package com.shiwuyu.RPCver1.Common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Program: RPCFromZero
 * @Description: 通用的Request的对象
 * @author 张非凡
 * @version  2022-11-03 16:42
 **/

/**
 * 实际应用中不可能只有一种类型的数据
 * 由此我们将传输对象抽象为Object对象
 * rpc需要经过网络传输，有可能失败，类似于http，我们引入状态码和状态信息来表示服务调用成功还是失败
 */
@Data
@Builder
public class RPCResponse implements Serializable {
    // 状态(码)信息
    private int code;
    private String message;
    // 具体数据
    private Object data;
    public static RPCResponse success(Object data) {
        return RPCResponse.builder().code(200).data(data).build();
    }
    public static RPCResponse fail() {
        return RPCResponse.builder().code(500).message("服务器发生错误").build();
    }

}
