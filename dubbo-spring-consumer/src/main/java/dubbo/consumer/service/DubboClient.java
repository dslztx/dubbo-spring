package dubbo.consumer.service;

import dubbo.api.domain.DubboServiceQuery;
import dubbo.api.domain.DubboServiceResult;
import dubbo.api.domain.Person;
import dubbo.api.service.DubboService;
import lombok.extern.slf4j.Slf4j;
import me.dslztx.assist.util.RandomAssist;
import org.apache.dubbo.rpc.RpcException;

import java.util.concurrent.TimeUnit;

@Slf4j
public class DubboClient {

    private static DubboService dubboService;

    static {
        init();
    }

    private static void init() {
        try {
            dubboService = (DubboService) SpringContainer.fetchBeanByName("dubboService");
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 100; i++) {
                Person person = new Person("dslztx", RandomAssist.randomInt(0, 100));

                try {
                    DubboServiceResult result = dubboService.invoke(new DubboServiceQuery(person));
                    log.info(result.getMsg());
                } catch (RpcException e) {
                    log.warn("", e);
                } catch (Exception e) {
                    log.error("", e);
                }

            }

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
    }

}
