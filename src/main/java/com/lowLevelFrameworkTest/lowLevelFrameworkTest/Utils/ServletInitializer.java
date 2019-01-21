package com.lowLevelFrameworkTest.lowLevelFrameworkTest.Utils;

import com.lowLevelFrameworkTest.lowLevelFrameworkTest.LowLevelORM.Omega;
import com.lowLevelFrameworkTest.lowLevelFrameworkTest.Model.Contact;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder){
        try {
            System.out.println("Registering the classes");
            Omega.register(Contact.class);
        } catch (Exception e) {
            System.out.println("Unable to register class");
            e.printStackTrace();
        }
        return applicationBuilder.sources(LowLevelFrameworkTestApplication.class);
    }
}
