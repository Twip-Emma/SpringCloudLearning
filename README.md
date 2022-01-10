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