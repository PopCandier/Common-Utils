package com.pop.encrypt.utils;

import com.pop.encrypt.inter.AppDao;
import com.pop.encrypt.pojo.Code;
import com.pop.encrypt.support.TokenTime;

import java.util.concurrent.TimeUnit;

/**
 * @author Pop
 * @date 2019/9/20 20:30
 */
public class BootStrap {

    public static void main(String[] args) throws Exception {

        String accessKey = Aec.generateKey64();//我们给予的密匙
        String appId ="Pop12138";//唯一的appid
        Code code = new Code(appId,"username","password");
        String signature = Aec.signature(code,accessKey);

        //去登陆
        clientLogin(code,signature);

        //==================== 以上为客户端的权限认证操作

        //==================== 以下为服务端接受了参数请求需要验证是否合法
        String token = "";
        if(Aec.checkSignature(signature, code, new AppDao() {
            @Override
            public String getAccessKeyByAppId(String appId) {
                //这里交给使用着实现，这里会访问数据库并使用appId查询到访问key
                return accessKey;
            }
        })){

            System.out.println("验证通过，发放token");
            token = Aec.token(TokenTime.SECOND,50);//设置20秒超时，不设置为300秒
            writeToken2Client(token);




        }else{
            System.out.println("验证失败，拒绝请求");
        }
        //假设这是用户请求的内容 带上了token
        String finalToken = token;
        Thread thread = new Thread(()->{

            while(true){

                if(Aec.checkToken(finalToken)){
                    System.out.println("还未过期，请求通过");
                }else{
                    System.out.println("已经过期，请求失败，拒绝请求");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        });

        thread.start();
        thread.join();

    }

    public static void clientLogin(Code code,String signature){}
    public static void writeToken2Client(String token){}

}
