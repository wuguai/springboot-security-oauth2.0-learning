package com.sweetmart.modules.coupon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentCouponController {
    private static final Logger log = LoggerFactory.getLogger(PaymentCouponController.class);

    public PaymentCouponController() {
    }

    @GetMapping({"/product/{id}"})
    public String getProduct(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        return String.format("product id : %s", id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/order/{id}"})
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        return String.format("order id : %s", id);
    }
}