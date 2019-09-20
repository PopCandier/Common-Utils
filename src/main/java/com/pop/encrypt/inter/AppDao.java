package com.pop.encrypt.inter;

import com.pop.encrypt.pojo.Code;

/**
 * 此接口将用于
 * {@link com.pop.encrypt.utils.Aec}
 * 方法体内需要自己实现访问数据的appId与accessKey的数据库映射表，并返回对应的accessKey
 * @see com.pop.encrypt.utils.Aec#checkSignature(String, Code, AppDao)
 *
 */
@FunctionalInterface
public interface AppDao {
    String getAccessKeyByAppId(String appId);
}
