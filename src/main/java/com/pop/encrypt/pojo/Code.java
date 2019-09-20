package com.pop.encrypt.pojo;


import lombok.Data;

import java.nio.ByteBuffer;

/**
 * @program: fire-control-20190911
 * @description: 记录码信息
 * @author: Pop
 * @create: 2019-09-19 15:40
 **/
@Data
public class Code {

    private String appId;
    private String username;
    private String password;

    public Code() {
    }

    public Code(String appId, String username, String password) {
        this.appId = appId;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return appId + username + password ;
    }
    //    private char crcCode=0xF;//标志位
//    private ByteBuffer buffer;//具体的值
//
//    public byte[] getData(){
//        ByteBuffer b = ByteBuffer.allocate(buffer.capacity()+2);
//        b.putChar(crcCode);
//        b.put(buffer);
//        return b.array();
//    }
}
