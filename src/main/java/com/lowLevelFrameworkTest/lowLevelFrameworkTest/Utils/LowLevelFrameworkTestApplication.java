package com.lowLevelFrameworkTest.lowLevelFrameworkTest.Utils;

import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.Omega;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.Model.Contact;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.LowLevelFrameworkTest.lowLevelFrameworkTest.*"})
@ServletComponentScan
@SpringBootApplication
public class LowLevelFrameworkTestApplication {

	public static void main(String[] args) {

		SpringApplication.run(LowLevelFrameworkTestApplication.class, args);

	}


}

