package com.rest.webservices.restfulwebservices.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("/v1/person")
    public PersonV1 retrievePersonV1(){
        return new PersonV1("Bob");
    }

    @GetMapping("/v2/person")
    public PersonV2 retrievePersonV2(){
        return new PersonV2(new Name("Bob", "Maha"));
    }



    @GetMapping(value = "/person/param", params = "version1")
    public PersonV1 retrievePersonParamV1(){
        return new PersonV1("Bob");
    }

    @GetMapping(value = "/person/param", params = "version2")
    public PersonV2 retrievePersonParamV2(){
        return new PersonV2(new Name("Bob", "Maha"));
    }



    @GetMapping(value = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 retrievePersonHeaderV1(){
        return new PersonV1("Bob");
    }

    @GetMapping(value = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 retrievePersonHeaderV2(){
        return new PersonV2(new Name("Bob", "Maha"));
    }



    @GetMapping(value = "/person/producers", produces = "application/vnd.company.app-v1+json")
    public PersonV1 retrievePersonProducerV1(){
        return new PersonV1("Bob");
    }

    @GetMapping(value = "/person/producers", produces = "application/vnd.company.app-v2+json")
    public PersonV2 retrievePersonProducerV2(){
        return new PersonV2(new Name("Bob", "Maha"));
    }
}
