package com.shiwuyu.RPCver2.Common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Program: RPCFromZero
 * @Description: User实体pojo类
 * @Author: 张非凡
 * @Create: 2022-10-15 23:06
 **/
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String userName;
    private Boolean sex;
}
