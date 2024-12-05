package dubbo.consumer.service;

import lombok.extern.slf4j.Slf4j;
import me.dslztx.assist.util.ObjectAssist;
import me.dslztx.assist.util.StringAssist;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class SpringContainer {

    private static ApplicationContext context = null;

    static {
        init();
    }

    private static void init() {
        try {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");

            log.info("load application context successfully");
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public static Object fetchBeanByName(String name) {
        if (ObjectAssist.isNull(context)) {
            throw new RuntimeException("no context loaded");
        }

        if (StringAssist.isBlank(name)) {
            throw new RuntimeException("illegal bean name");
        }

        return context.getBean(name);
    }

}
