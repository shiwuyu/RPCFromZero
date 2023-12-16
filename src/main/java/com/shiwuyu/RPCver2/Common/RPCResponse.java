package com.shiwuyu.RPCver2.Common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Program: RPCFromZero
 * @Description: 通用的Request的对象
 * @Author: 张非凡
 * @Create: 2022-11-03 16:42
 **/
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
