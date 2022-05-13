package com.Project_2_Location_Search_API.controllers;

import com.Project_2_Location_Search_API.service.MapService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/map")
public class MapController {

    @Setter(onMethod =@__({@Autowired}))
    private MapService mapService;

    @GetMapping("/postal")
    public void postalCodeQuery(@RequestParam String postalcode, @RequestParam String countrycodes, @RequestParam String format) {
        mapService.getByPostalCode(postalcode, countrycodes, format);
    }

    @GetMapping("/structured")
    public void structuredQuery(@RequestParam String street, @RequestParam String city, @RequestParam String county, @RequestParam String state, @RequestParam String country, @RequestParam String postalcode, @RequestParam String format) {
        mapService.getByStructured(street, city, county, state, country, postalcode, format);
    }

    @GetMapping("/limitCountry")
    public void limitCountry(@RequestParam String q, @RequestParam String format, @RequestParam String countrycodes) {
        mapService.getByLimitCountry(q, format, countrycodes);
    }

    @GetMapping("/fromQuery")
    public void fromQuery(@RequestParam String q, @RequestParam String format) {
        mapService.getByQuery(q, format);
    }

    @GetMapping("/general")
    public void general(@RequestParam String q) {
        mapService.getGeneral(q);
    }
}
