<!--
 * @Author: 七画一只妖
 * @Date: 2022-01-10 11:37:56
 * @LastEditors: 七画一只妖
 * @LastEditTime: 2022-01-10 14:08:15
 * @Description: file content
-->
# SpringCloud学习笔记Day1
## Eureka
### 引入依赖<服务端>
~~~xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
~~~

### 引入依赖<客户端>
~~~xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>s
</dependency>
~~~

### eureka基本配置
~~~yml
server:
  port: 10086
spring:
  application:
    name: eurekaserver # eureka服务名称
eureka:
  client:
    service-url: # eureka的地址信息
      defaultZone: http://127.0.0.1:10086/eureka
~~~


## Ribbon负载均衡
### 使用负载均衡
~~~java
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
~~~
### 配置负载均衡规则（容器设置）
~~~java
    @Bean
    public IRule randomRule(){
        //将负载均衡规则设置为随机
        return new RandomRule();
    }
~~~
### 配置负载均衡规则（配置文件设置）
~~~yml
userservice:
    ribbon:
        NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule # 负载均衡规则
~~~
### 配置饥饿加载（默认懒加载）
~~~yml
ribbon:
  eager-load:
    enabled: true # 饥饿加载：开
    clients: # 指定要饥饿加载的服务
      - userservice
~~~

## Nacos
### 引入依赖（夫工程）
~~~xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dependencies</artifactId>
    <version>2.2.5.RELEASE</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
~~~
### 客户端依赖
```xml
<!-- nacos客户端依赖包 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```
### 设置集群
~~~yml
spring:
  cloud:
    nacos:
      discovery:
        cluster-name: HZ # 设置集群名称
~~~
### 设置负载均衡（同集群优先）
~~~yml
userservice:
  ribbon:
    NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule # 负载均衡规则
~~~
### 设置命名空间
~~~yml
spring:
  cloud:
    nacos:
      discovery:
        namespace: 你的环境ID
~~~
### 设置是否是临时实例
~~~yml
spring:
  cloud:
    nacos:
      discovery:
        ephemeral: false # 设置是否是临时实例
~~~

# SpringCloud学习笔记Day2
## Nacos
### 引入nacos配置管理依赖
~~~xml
<!--引入nacos的配置依赖-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
~~~
### 引导文件bootstrap.yml基本配置
~~~yml
spring:
  application:
    name: userservice
  profiles:
    active: dev # 环境
  cloud:
    nacos:
      server-addr: localhost:8848 # nacos地址
      config:
        file-extension: yaml # 文件后缀
~~~
### nacos配置热更新
~~~java
  @Data
  @Component
  @ConfigurationProperties(prefix = "pattern")
  public class PatternProperties {
  private String dateformat;
}
~~~