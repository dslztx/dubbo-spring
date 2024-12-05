package app;

import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DubboApplication {

    public static void main(String[] args) {

        log.info("dubbo application is starting");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 无需显示调用context.start()方法，ClassPathXmlApplicationContext会在创建后立即启动
        // context.start();

        // 走到这里已经依次完成：
        // 1、Bean的初始化，post-init方法 / @PostConstruct注解方法的执行
        // 2、dubbo服务初始化完成，并向注册中心注册（具体源码入口可参见org.apache.dubbo.config.ServiceConfig#export）
        // 即满足相应的happens-before条件
        log.info("dubbo application has started");

        // 保持主线程活，避免整个进程退出
        keepAppAlive();
    }

    private static void keepAppAlive() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.DAYS.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("", e);
                    }
                }
            }
        }).start();
    }

}
