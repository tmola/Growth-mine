package utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 服务程序工具类
 *
 * @author jintingying
 * @version 1.0
 * @date 2019/12/11
 */
@Slf4j
@Component
public class AppUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppUtil.context = context;
    }

    /**
     * 获取 Spring 容器中定义的所有 JavaBean 的名称
     */
    public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }

    /**
     * 根据名称获取Spring IoC容器管理的JavaBean对象
     */
    public static Object getBeanByName(String beanName) {
        return context == null ? null : context.getBean(beanName);
    }

    /**
     * 打印服务启动信息
     */
    public static void info(ConfigurableApplicationContext application) {
        Environment env = application.getEnvironment();
        String ip = "127.0.0.1";
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n\t----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local:       http://localhost:" + port + path + "/\n\t" +
                "External:    http://" + ip + ":" + port + path + "/\n\t" +
                "Swagger-ui:  http://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------------");
    }
}
