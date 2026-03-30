package com.bank.loan.LoanApplicationServiceApplication.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.loan.LoanApplicationServiceApplication.dto.NotificationRequest;

@FeignClient(name = "notification-service", url = "${notification.service-url}")
public interface NotificationClient {
    @PostMapping
    void send(@RequestBody NotificationRequest request);
}
