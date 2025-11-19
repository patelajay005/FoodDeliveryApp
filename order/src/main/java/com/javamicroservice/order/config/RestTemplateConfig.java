package com.javamicroservice.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new JwtTokenInterceptor()));
        return restTemplate;
    }

    private static class JwtTokenInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(
                HttpRequest request,
                byte[] body,
                ClientHttpRequestExecution execution) throws IOException {
            
            String authHeader = null;
            
            // First, try to get the Authorization header from the current HttpServletRequest
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest httpServletRequest = attributes.getRequest();
                authHeader = httpServletRequest.getHeader("Authorization");
            }
            
            // If Authorization header exists, add it to the outgoing request
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                request.getHeaders().set("Authorization", authHeader);
            } else {
                // Log warning if no token found (for debugging)
                System.out.println("Warning: No JWT token found in request context for inter-service call to: " + request.getURI());
            }
            
            return execution.execute(request, body);
        }
    }
}

