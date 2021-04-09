package com.web.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@MapperScan(basePackages = "com.web.blog.dao")
public class WebBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebBlogApplication.class, args);
	}

}

