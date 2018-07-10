package com.daishuai.wechatsell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@MapperScan(basePackages = "com.daishuai.wechatsell.mapper")
@EnableCaching
public class SpringbootWechatsellApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWechatsellApplication.class, args);
	}
}
