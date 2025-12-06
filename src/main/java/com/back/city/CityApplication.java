package com.back.city;

import com.back.city.initializator.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CityApplication {
    private static Initializer initializer;

    @Autowired
    public void setInitializer(Initializer initializer){
        CityApplication.initializer = initializer;
    }

	public static void main(String[] args) {
        SpringApplication.run(CityApplication.class, args);
        //initializer.initial();
	}

}
