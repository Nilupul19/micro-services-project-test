package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.model.Inventory;
import com.example.repo.InventoryRepo;

@SpringBootApplication
public class InventeryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventeryServiceApplication.class, args);
	}
    
	@Bean
    public CommandLineRunner saveData(InventoryRepo inventoryRepo){
     return args -> {
           
		     Inventory inventory1 = new Inventory();
			 inventory1.setSkuCode("Samsung A5");
			 inventory1.setQuantity(16);

			 Inventory inventory2 = new Inventory();
			 inventory2.setSkuCode("Nokia 6");
			 inventory2.setQuantity(12);
             
			 inventoryRepo.save(inventory1);
			 inventoryRepo.save(inventory2);


	 };

	}



}
