package com.shiwuyu.RPCver2.Server;

import com.shiwuyu.RPCver2.Common.Blog;
import com.shiwuyu.RPCver2.Service.BlogService;

/**
 * 服务器新的服务接口实现类
 *
 * @author 张非凡
 * @version 1.0.0
 * @date 2023-12-05 10:18
 */
public class BlogServiceImpl implements BlogService {

    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("我的博客").userId(1122).build();
        System.out.println("客户端查询了"+ id + "博客");
        return blog;
    }
}
