package com.gentle;


import com.alibaba.dubbo.container.Main;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Gentle
 * @date 2019/03/26 : 16/35
 */
@SpringBootApplication
@MapperScan(value = "com.gentle.mapper")
public class DubboServiceUserProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServiceUserProviderApplication.class, args);
        Main.main(args);

    }

}
