package com.bank.loan.LoanApplicationServiceApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;

@Component
public class FeignConfig {

	@Bean
	public RequestInterceptor requestInterceptor() {
		return template -> {
			RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
			if (attrs instanceof ServletRequestAttributes s) {
				String auth = s.getRequest().getHeader("Authorization");
				if (auth != null)
					template.header("Authorization", auth);
			}
		};
	}
}
