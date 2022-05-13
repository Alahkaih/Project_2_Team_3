package com.Project_2_Location_Status_API.Controllers;

import com.Project_2_Location_Status_API.Services.CovidApiService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/covid-api")
public class CovidApiController {



    @Setter(onMethod = @__({@Autowired}))
    private CovidApiService covidApiService;

    @GetMapping("search") // covid-api/search?country=nameOfCountry
    public ResponseEntity<String> getDataByCountry(@RequestParam String country){
    return ResponseEntity.ok(covidApiService.getAllData(country).getBody());
    }

}
