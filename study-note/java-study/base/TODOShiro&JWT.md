## 1. Shiro的关注点
>
+ SecurityManager，可以理解成控制中心，所有请求最终基本上都通过它来代理转发，一般我们程序中不需要直接跟他打交道。
+ Subject ，请求主体。比如登录用户，比如一个被授权的app。在程序中任何地方都可以通过SecurityUtils.getSubject()获取到当前的subject。subject中可以获取到Principal，这个是subject的标识，比如登陆用户的用户名或者id等，shiro不对值做限制。但是在登录和授权过程中，程序需要通过principal来识别唯一的用户。
+ Realm，这个实在不知道怎么翻译合适。通俗一点理解就是realm可以访问安全相关数据，提供统一的数据封装来给上层做数据校验。shiro的建议是每种数据源定义一个realm，比如用户数据存在数据库可以使用JdbcRealm；存在属性配置文件可以使用PropertiesRealm。一般我们使用shiro都使用自定义的realm。
当有多个realm存在的时候，shiro在做用户校验的时候会按照定义的策略来决定认证是否通过，shiro提供的可选策略有一个成功或者所有都成功等。
一个realm对应了一个CredentialsMatcher，用来做用户提交认证信息和realm获取得用户信息做比对，shiro已经提供了常用的比如用户密码和存储的Hash后的密码的对比。
https://shiro.apache.org

## 2. Jwt的关注点
```java
//TODO
```

## jwt
1. 依赖
2. 在用户实体类实现token的生成方法
3. 实现注解用于标记哪些方法需要进行验证
4. 实现拦截
## shiro
1. 依赖
2. config
 + 过滤
 + menager
 + realm

## 3. 使用
1. 导入依赖
```xml
 <!-- jwt处理框架-->
<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>${jwt.version}</version>
</dependency>
<!--shiro权限管理框架-->
<dependency>
   <groupId>org.apache.shiro</groupId>
   <artifactId>shiro-spring</artifactId>
   <version>${shiro.version}</version>
</dependency>
<dependency>
   <groupId>org.apache.shiro</groupId>
   <artifactId>shiro-ehcache</artifactId>
   <version>${shiro.version}</version>
</dependency>
```

2. shiro配置
ShiroConfiguration
首先是初始化shiro的bean，主要是初始化Realm，注册Filter，定义filterChain。
```Java
@Configuration
public class ShiroConfig {
    /**
     * 注册shiro的Filter，拦截请求
     */
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager,UserService userService) throws Exception{
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
        filterRegistration.setFilter((Filter)shiroFilter(securityManager, userService).getObject());
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);

        return filterRegistration;
    }

    /**
     * 初始化Authenticator
     */
    @Bean
    public Authenticator authenticator(UserService userService) {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
        authenticator.setRealms(Arrays.asList(jwtShiroRealm(userService), dbShiroRealm(userService)));
        //设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    /**
    * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
    * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
    */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }
    /**
    * 用于用户名密码登录时认证的realm
    */
    @Bean("dbRealm")
    public Realm dbShiroRealm(UserService userService) {
        DbShiroRealm myShiroRealm = new DbShiroRealm(userService);
        return myShiroRealm;
    }
    /**
    * 用于JWT token认证的realm
    */
    @Bean("jwtRealm")
    public Realm jwtShiroRealm(UserService userService) {
        JWTShiroRealm myShiroRealm = new JWTShiroRealm(userService);
        return myShiroRealm;
    }

    /**
     * 设置过滤器，将自定义的Filter加入
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, UserService userService) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = factoryBean.getFilters();
        filterMap.put("authcToken", createAuthFilter(userService));
        filterMap.put("anyRole", createRolesFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return factoryBean;
    }

    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login", "noSessionCreation,anon");  //login不做认证，noSessionCreation的作用是用户在操作session时会抛异常
        chainDefinition.addPathDefinition("/logout", "noSessionCreation,authcToken[permissive]"); //做用户认证，permissive参数的作用是当token无效时也允许请求访问，不会返回鉴权未通过的错误
        chainDefinition.addPathDefinition("/image/**", "anon");
        chainDefinition.addPathDefinition("/admin/**", "noSessionCreation,authcToken,anyRole[admin,manager]"); //只允许admin或manager角色的用户访问
        chainDefinition.addPathDefinition("/article/list", "noSessionCreation,authcToken");
        chainDefinition.addPathDefinition("/article/*", "noSessionCreation,authcToken[permissive]");
        chainDefinition.addPathDefinition("/**", "noSessionCreation,authcToken"); // 默认进行用户鉴权
        return chainDefinition;
    }
    //注意不要加@Bean注解，不然spring会自动注册成filter
    protected JwtAuthFilter createAuthFilter(UserService userService){
        return new JwtAuthFilter(userService);
    }
    //注意不要加@Bean注解，不然spring会自动注册成filter
    protected AnyRolesAuthorizationFilter createRolesFilter(){
        return new AnyRolesAuthorizationFilter();
    }

}
```

## 4. API
>https://shiro.apache.org/10-minute-tutorial.html#download
+ 获取当前正在执行的用户:`Subject currentUser = SecurityUtils.getSubject();`
+ 得到他们的会话:
```
Session session = currentUser.getSession();
session.setAttribute( "someKey", "aValue" );
//Session 是一个特定于 shiro 的实例，它提供了常规 HttpSessions 中的大部分内容，但是有一些额外的好处和一个很大的不同: 它不需要 HTTP 环境！
```
+ 那些真正有用的东西呢，比如检查他们是否被允许做一些事情，比如检查角色和权限？
```
if ( !currentUser.isAuthenticated() ) {
    //collect user principals and credentials in a gui specific manner
    //such as username/password html form, X509 certificate, OpenID, etc.
    //We'll use the username/password example here since it is the most common.
    //(do you know what movie this is from? ;)
    UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
    //this is all you have to do to support 'remember me' (no config - built in!):
    token.setRememberMe(true);
    currentUser.login(token);
}
```
+ 但是如果他们的登录尝试失败了怎么办？ 你可以捕捉到各种各样的特定异常，这些异常告诉你到底发生了什么，并允许你处理和相应地做出反应:
```
try {
    currentUser.login( token );
    //if no exception, that's it, we're done!
} catch ( UnknownAccountException uae ) {
    //username wasn't in the system, show them an error message?
} catch ( IncorrectCredentialsException ice ) {
    //password didn't match, try again?
} catch ( LockedAccountException lae ) {
    //account for that username is locked - can't login.  Show them a message?
}
    ... more types exceptions to check if you want ...
} catch ( AuthenticationException ae ) {
    //unexpected condition - error?
}
```
+ 我们已经有一个登录用户了，我们还能做什么？
```
//Let’s say who they are:
log.info( "User [" + currentUser.getPrincipal() + "] logged in successfully." );
//还可以测试它们是否具有特定的作用:
if ( currentUser.hasRole( "schwartz" ) ) {
    log.info("May the Schwartz be with you!" );
} else {
    log.info( "Hello, mere mortal." );
}
//还可以看到他们是否有权限对某种类型的实体进行操作:
if ( currentUser.isPermitted( "lightsaber:weild" ) ) {
    log.info("You may use a lightsaber ring.  Use it wisely.");
} else {
    log.info("Sorry, lightsaber rings are for schwartz masters only.");
}
//还可以执行一个非常强大的实例级权限检查——检查用户是否有能力访问某个类型的特定实例:
if ( currentUser.isPermitted( "winnebago:drive:eagle5" ) ) {
    log.info("You are permitted to 'drive' the 'winnebago' with license plate (id) 'eagle5'.  " +
                "Here are the keys - have fun!");
} else {
    log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
}
//最后，当用户使用完应用程序后，他们可以注销:
currentUser.logout();
```
