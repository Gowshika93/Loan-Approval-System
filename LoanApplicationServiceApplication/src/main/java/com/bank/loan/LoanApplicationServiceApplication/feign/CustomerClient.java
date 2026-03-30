package com.bank.loan.LoanApplicationServiceApplication.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.loan.LoanApplicationServiceApplication.dto.CustomerProfile;

@FeignClient(name = "customer-service", url = "${customer.service-url}")
public interface CustomerClient {
    @GetMapping("/{id}")
    CustomerProfile getCustomer(@PathVariable("id") Long id);
}
