package mq.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: 七画一只妖
 * @Date: 2022/1/16 14:44
 */
@Component
public class SpringAMQPListener {

    @RabbitListener(queues = "simple.queue")
    public void listerSimpleQueueMessage(String msg) throws InterruptedException {
        System.out.println("spring 消费者收到了消息[ " + msg + " ]");
    }
}
