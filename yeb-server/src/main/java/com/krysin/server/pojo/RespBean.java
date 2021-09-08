package com.krysin.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//公共返回对象
@Data
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    //成功返回结果
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    //重写成功返回结果
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    //返回失败结果
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    //重写返回失败结果
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}
