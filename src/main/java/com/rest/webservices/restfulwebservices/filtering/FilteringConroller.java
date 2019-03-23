package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringConroller {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans(){
        List<SomeBean> someBeans = Arrays.asList(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value12", "value22", "value32")
        );

        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        mapping.setFilters(filterProvider);

        return mapping;
    }


}
