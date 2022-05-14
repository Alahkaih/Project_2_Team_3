package com.Project_2_Location_Status_API.Controllers;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.Project_2_Location_Status_API.Services.CovidApiService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid-api")
public class CovidApiController {



    @Setter(onMethod = @__({@Autowired}))
    private CovidApiService covidApiService;

    @GetMapping("search") // covid-api/search?country=nameOfCountry
    public ResponseEntity<CovidStatsDTO> getDataByCountry(@RequestParam String country){
        return ResponseEntity.ok(covidApiService.getAllDataByCountry(country).getBody());
    }

    @GetMapping("calculate")
    public ResponseEntity<String> getStatusBasedOnLocation(@RequestParam String country) {

        ResponseEntity<CovidStatsDTO> response = covidApiService.getAllDataByCountry(country);
        Integer score = covidApiService.calculateStatus(response);

        if (score < 50) {
            return ResponseEntity.ok().body("This locations is currently unsafe to visit.");
        }
        return ResponseEntity.ok().body("This location is currently safe to visit.");
    }

    @GetMapping("vaccine-data") // covid-api/vaccine-data?country=nameOfCountry
    public ResponseEntity<VaccineDataDTO> getVaccineDataByCountry(@RequestParam String country){
        return ResponseEntity.ok(covidApiService.getAllVaccineDataByCountry(country).getBody());
    }


}
