package cn.edu.bupt.yaoxintong.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //Demo1Application这个类是SpringApplication.run启动类
        return application.sources(YaoxintongAdminApplication.class);
    }
}
