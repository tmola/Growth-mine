package com.uniform;

import com.uniform.common.utils.ToolUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

@EnableSwagger2
@EnableTransactionManagement
@SpringBootApplication
public class UniformCodeApplication
//        implements TransactionManagementConfigurer
{
//
//    @Resource(name="txManager2")
//    private PlatformTransactionManager txManager2;
//
////    // 创建事务管理器1
////    @Bean(name = "txManager1")
////    public PlatformTransactionManager txManager(DataSource dataSource) {
////        return new DataSourceTransactionManager(dataSource);
////    }
//
//    // 创建事务管理器2
//    @Bean(name = "txManager2")
//    public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }
//
//    // 实现接口 TransactionManagementConfigurer 方法，其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return txManager2;
//    }


    public static void main(String[] args) {

        ToolUtil.appRun(UniformCodeApplication.class, args);

    }

}
