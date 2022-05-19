package com.Project_2_Location_Status_API.Controllers;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.Project_2_Location_Status_API.Entities.StatusReport;
import com.Project_2_Location_Status_API.Services.CovidApiService;
import com.Project_2_Location_Status_API.Services.StatusService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("status")
public class StatusController {

    @Setter(onMethod =@__({@Autowired}))
    private StatusService statusService;
    @Setter(onMethod =@__({@Autowired}))
    private CovidApiService covidApiService;

//    @GetMapping("{location}")
//    public ResponseEntity getStatusByLocation(@PathVariable String location) {
//        return ResponseEntity.ok(statusService.getStatusByLocation(location));
//    }

    @PostMapping
    public ResponseEntity saveNewStatus(@RequestBody StatusReport statusReport) {
        try {
            statusService.saveNewStatus(statusReport);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error creating statusReport");
        }
        return ResponseEntity.ok("Successfully created new statusReport for " + statusReport.getLocation());
    }

    @GetMapping("{country}")
    public ResponseEntity getStatusBasedOnLocation(@PathVariable String country) {

        StatusReport statusReport = new StatusReport();
        CovidStatsDTO covidStats = covidApiService.getAllDataByCountry(country).getBody();
        VaccineDataDTO vaccineStats = covidApiService.getAllVaccineDataByCountry(country).getBody();

        statusReport.setLocation(country);
        statusReport.setScore(statusService.calculateScore(covidStats, vaccineStats));
        statusReport.setCreationDate(LocalDate.now());
        statusService.saveNewStatus(statusReport);
        return ResponseEntity.ok(statusService.calculateStatusReport(statusReport.getScore()));
    }
}
