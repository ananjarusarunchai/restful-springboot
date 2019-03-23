package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
public class HelloWorldController {

//  @RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
    @Autowired
    MessageSource messageSource;


    @GetMapping(path = "/helloWorld")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/helloWorld-internationalize")
    public String helloWorldInternationalize(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

    @GetMapping(path = "/helloWorld-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/helloWorld-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable  String name){
        return new HelloWorldBean(String.format("Hello World Bean %s", name));
    }
}
