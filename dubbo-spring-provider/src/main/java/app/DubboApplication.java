package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboApplication {

    private static final Logger logger = LoggerFactory.getLogger(DubboApplication.class);

    public static void main(String[] args) {

        logger.info("Dubbo Application is starting");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 无需显示调用context.start()方法，ClassPathXmlApplicationContext会在创建后立即启动
        // context.start();

        // dubbo在
        // org.apache.dubbo.config.ServiceConfig.class:59
        // org.apache.dubbo.config.ServiceConfig#export
        // 走到这里已经依次完成：1）Bean的post-init方法（）初始化方法/@PostConstruct；2）dubbo服务初始化完成，并向注册中心注册（happens-before），详细后续可看源码
        logger.info("Dubbo Application has started");

        keepAppAlive();
    }

    private static void keepAppAlive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(24 * 3600 * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
