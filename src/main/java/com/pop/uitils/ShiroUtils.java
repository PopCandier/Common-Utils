package com.pop.uitils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;


/**
 * @program:
 * @description: for shiro
 * @author: Pop
 * @create: 2019-04-25 15:39
 **/
public class ShiroUtils {

    private static final String _SALT = "cxk";//公开盐
    private static final String _PRIVATE_SALT = "jinitaimei";//私密盐
    private static final String _DEFAULT_CODING="utf-8";
    private static final Integer _HASH_NUMBER=2;//加密次数

    private static final String ALGORITHMNAME_MD5="MD5";
    private static final String ALGORITHMNAME_SHA512="SHA-512";
    private static final String ALGORITHMNAME_SHA1="SHA-1";


    public static void main(String[] args) {
        String src = "123";
//        String base64_e=encodeHex(src);
//        String base64_d=decodeHex(base64_e);
//        Assert.assertEquals(base64_d,src);

        System.out.println(serviceEncode(src));
        System.out.println(encodeMd5(src));
    }

    public static String byteToString(byte[] bytes,String encoding){ return CodecSupport.toString(bytes,encoding); }
    public static String byteToString(byte[] bytes){ return byteToString(bytes,_DEFAULT_CODING);}
    public static byte[] stringToByte(String src){return stringToByte(src,_DEFAULT_CODING);}
    public static byte[] stringToByte(String src,String encoding){return CodecSupport.toBytes(src,encoding);}

    //***************** 加密 start

    private static DefaultHashService hashService;
    private static HashRequest.Builder builder;
    private static String randomString;
    static {
        hashService =new DefaultHashService();
        hashService.setHashAlgorithmName(ALGORITHMNAME_SHA512);//设置加密名称
        hashService.setPrivateSalt(new SimpleByteSource(_PRIVATE_SALT));//设置私盐
        hashService.setGeneratePublicSalt(true);//是否需要生成公盐，默认false
        hashService.setHashIterations(_HASH_NUMBER);//加密次数
        //用于生成随机数
        SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
        generator.setSeed(_SALT.getBytes());//设置随机种子
        randomString =generator.nextBytes().toHex();

        hashService.setRandomNumberGenerator(generator);//设置随机生成器
        builder = new HashRequest.Builder()
                .setAlgorithmName(ALGORITHMNAME_MD5)
                .setSalt(ByteSource.Util.bytes(randomString))
                .setIterations(_HASH_NUMBER);

    }
    public static String serviceEncode(String src){
        return hashService.computeHash(builder.setSource(ByteSource.Util.bytes(src.getBytes())).build()).toString();
    }



    /**
     * Base64 加密
     * @param src
     * @return
     */
    public static String encodeBase64(String src){ return Base64.encodeToString(src.getBytes()); }

    /**
     * Base64 解密
     * @param src
     * @return
     */
    public static String decodeBase64(String src){ return Base64.decodeToString(src.getBytes());}

    /**
     * 16 进制 加密
     * @param src
     * @return
     */
    public static String encodeHex(String src){ return Hex.encodeToString(src.getBytes());}

    /**
     * 16 进制 解密
     * @param src
     * @return
     */
    public static String decodeHex(String src){ return new String(Hex.encodeToString(src.getBytes()));}

    /**
     * MD5加密
     * @param src
     * @return
     */
    public static String encodeMd5(String src){ return encodeMd5(src,_SALT,_HASH_NUMBER);}

    /**
     * MD5加密
     * @param src
     * @param salt 指定盐
     * @param hashNumber 加密次数
     * @return
     */
    public static String encodeMd5(String src,String salt,Integer hashNumber){ return new Md5Hash(src,salt,hashNumber).toString(); }

    public static String encodeMd2Base64(String src,String salt,Integer hashNumber){ return new Md5Hash(src,salt,hashNumber).toBase64(); }
    public static String encodeMd2Base64(String src){return encodeMd2Base64(src,_SALT,_HASH_NUMBER);}

    public static String encodeMd2Hex(String src,String salt,Integer hashNumber){ return new Md5Hash(src,salt,hashNumber).toHex(); }
    public static String encodeMd2Hex(String src){return encodeMd2Base64(src,_SALT,_HASH_NUMBER);}

    //***************** 加密 end


}
