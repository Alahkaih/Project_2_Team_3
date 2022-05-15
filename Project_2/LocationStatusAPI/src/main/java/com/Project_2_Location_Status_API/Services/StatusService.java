package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class StatusService {

    @Setter(onMethod = @__({@Autowired}))
    private StatusRepository statusRepository;

    public Status getStatusByLocation(String location) {
        Status op = statusRepository.findByLocation(location);
        if (op != null) {
            return op;
        } else {
            throw new NullPointerException(location + " could not be found");
        }
    }

    public void createNewStatus(Status status) {
        if(status.getLocation() == null || status.getLocation().isEmpty()) {
            throw new NullPointerException("Can't create a status without a location");
//        } else if(status.getScore() == null || status.getScore() < 0) {
//            throw new NullPointerException("Can't create a status with a null or negative score");
        } else if(status.getCreationDate() == null) {
            throw new NullPointerException("Can't create a status with a null creation date");
        } else if(status.getCreationDate().isAfter(LocalDate.now())) {
            throw new NullPointerException("Can't create a status with a date in the future");
        } else {
            statusRepository.save(status);
        }
    }


    public String calculateStatus (ResponseEntity<CovidStatsDTO> covidStats,ResponseEntity<VaccineDataDTO> vaccineStats) {

//        Can access the response to get whatever information you need for the calculations (check below for example)
//        String cases = response.getBody().getCases();

        if (covidStats.getBody() == null || vaccineStats.getBody() == null)
            throw new NullPointerException("No data found for country provided");

        int numOfPopVaccinated = vaccineStats.getBody().getTimeline().fields().next().getValue().asInt();
        int totalPopulation = covidStats.getBody().getPopulation();
        String status;


        if (numOfPopVaccinated > totalPopulation * 0.8) {
            status = "Safe to travel";
            createNewStatus(covidStats.getBody().getCountry(),status,LocalDate.now().toString());
            return status;
        } else if (numOfPopVaccinated > totalPopulation * 0.4) {
            return "Proceed with caution";
        } else {
            return "Not safe";
        }
    }

}
