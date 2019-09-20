#### Aes 加密工具

> Quick Start

服务端会生成密钥给客户，客户端使用这个密匙和appid进行登录操作

```java
String accessKey = Aec.generateKey64();//我们给予的密匙
String appId ="Pop12138";//唯一的appid
Code code = new Code(appId,"username","password");
String signature = Aec.signature(code,accessKey);

//去登陆
clientLogin(code,signature);
```

密匙和appid可能在用户注册的时候就会唯一指定，并与账号和密码关联起来，每个用户的appid和accessKey都是唯一的。

对于服务端而言，需要验证签名是否合法，使用appid去数据库索引到对饮的accesskey进行解密，解密成功表示鉴权成功。

```java
 if(Aec.checkSignature(signature, code, new AppDao() {
            @Override
            public String getAccessKeyByAppId(String appId) {
                //这里交给使用着实现，这里会访问数据库并使用appId查询到访问key
                return accessKey;
            }
        })){

            System.out.println("验证通过，发放token");
            token = Aec.token(TokenTime.SECOND,20);//设置20秒超时，不设置为300秒
            writeToken2Client(token);
            
        }else{
            System.out.println("验证失败，拒绝请求");
        }
```

以后的每次请求，都必须带上token，token失效有两种情况，token解析失败，或者超时。这两种情况都会拒绝请求，并且要求重新登陆或者重新发放token

```JAVA
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
```

