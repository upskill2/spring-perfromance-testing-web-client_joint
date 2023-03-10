package com.spring.demo.springperfromancetestingwebclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

    @Autowired
    WebClient webClient;

    @GetMapping (value = "/customer/{customerId}")
    public CustomerDetails getCustomerDetails (@PathVariable String customerId) {

        String vehicleUrl = "http://localhost:8082/customer/" + customerId + "/vehicleDetails";
        VehicleDetails vehicleResponse = webClient
                .get ()
                .uri (vehicleUrl)
                .retrieve ()
                .bodyToMono (VehicleDetails.class).block ();

        String contractUrl = "http://localhost:8081/customer/" + customerId + "/contractDetails";
        ContractDetails contractResponse = webClient
                .get ()
                .uri (contractUrl)
                .retrieve ()
                .bodyToMono (ContractDetails.class).block ();

        return new CustomerDetails (contractResponse.contractId (), contractResponse.contractName (), contractResponse.postalCode (),
                vehicleResponse.carType (), vehicleResponse.licensePlate ());
    }

    @GetMapping("/servicestatus")
    public String getStatus(){
        return "up";
    }


}
