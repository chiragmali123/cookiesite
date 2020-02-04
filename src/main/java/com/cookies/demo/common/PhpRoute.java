package com.cookies.demo.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * Created by akshay on 29/7/19
 *
 * @author akshay
 * RestApiV2
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping({"/php"})
public @interface PhpRoute {
}
