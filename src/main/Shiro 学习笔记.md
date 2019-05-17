### Shiro 学习笔记

#### 添加依赖

```xml
<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.25</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-api</artifactId>
      <version>1.7.25</version>
    </dependency>
    <dependency>
      <groupId>commons-logging</groupId>
      <artifactId>commons-logging</artifactId>
      <version>1.1.3</version>
    </dependency>
    <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-core</artifactId>
      <version>1.2.2</version>
    </dependency>
```

#### 一个小demo

```java
@Test
    public void testHelloworld(){

        //注册工厂
        Factory<SecurityManager> factory
                = new IniSecurityManagerFactory("classpath:shiro.ini");
        //设置实例
        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        //得到Subject 以及粗昂见用户名密码身份验证Token
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");


        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //断言
        assert true:subject.isAuthenticated();

        //退出
        subject.logout();
    }
```

* 首先通过new IniSecurityManagerFactory并指定一个ini配置到相应目录来创建一个工厂
* 然后获取一个SecurityManager并绑定到SecurityUtils，这是一个全局变量，设置一次。
* 通过SecurityUtils获得Subject，会自动绑定到当前线程，如果在web环境在请求`结束时候需要解除绑定`

获取身份验证的Token，这里用的是用户名密码验证，算是一种策略吧。

* 然后使用subject.login进行登陆。会委托给SecurityManager.login登陆

  如 果 身份验证 失败请捕 获 AuthenticationException 或 其 子 类 ， 常 见 的 如 ：
  DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、
  UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过
  多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的
  凭证）等



#### 关于身份认证的流程

虽然是`subject.login(token)`调用验证，实际也是委托给了`SecurityManager`，不过在那之前需要先认证

不过，`SecurityManager`是`shiro`核心的安全管理器，但是实际上也是委托给了`Authenticator`去进行身份

的验证。

`Authenticator`可以定义属于自己的实现，内置了策略，也就是响应的`AuthenticatorStrategy`进行

多`Realm`的身份验证



#### Realm

`Shiro`的安全仓库，需要从里面获得角色，用户，权限等内容。当`SecurityManager`需要获得验证用户身份的时候，那么就需要从Realm中查看是否合法

```java
public interface Realm {

    String getName();

    boolean supports(AuthenticationToken token);

    AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException;

}

```

所以，让我们需要自定义`Realm`的时候

```ini
[main]
# 声明一个realm
myRealm1=com.pop.shiro.realm.MyRealm1
# 指定实现
securityManager.realms=$myRealm1
```

自定义的内容

```java
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();
        String password = (String)token.getCredentials();

        if(!"zhan".equals(username)){
            throw new UnknownAccountException();//账号错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();//密码错误
        }

        //返回用户信息
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
```



### PasswordService/CredentialsMatcher

Shiro提供了PasswordService及CredentialsMatcher用于提供加密密码及验证加密功能。

他们两个提供了分别的实现，PasswordService的默认实现

DefaultPasswordServiced，还有HashingCredentialsMatcher

的实现，前者提供密码加密，后者提供解密或者验证id



ZR_SHLX :商会类型

ZR_SFTJDJ：

江西省（包括下级组织）商会是否在民政部门登记统计 1

江西省（包括下级组织）商会是否建有党组织统计  2

江西省（包括下级组织）商会工作人员情况统计 3  





ZR_SHINFO：商会信息表