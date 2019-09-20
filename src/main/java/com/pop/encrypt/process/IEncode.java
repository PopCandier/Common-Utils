package com.pop.encrypt.process;


import com.pop.encrypt.pojo.Code;

/**
 * 加密接口
 */
public interface IEncode {
    byte[] encode(Code code);
}
