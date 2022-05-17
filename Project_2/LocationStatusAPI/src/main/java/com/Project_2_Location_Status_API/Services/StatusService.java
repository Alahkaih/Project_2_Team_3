package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.Project_2_Location_Status_API.Entities.Status;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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

    public void saveNewStatus(Status status) {
        if (status.getLocation() == null || status.getLocation().isEmpty()) {
            throw new NullPointerException("Can't create a status without a location");
        } else if (status.getScore() == 0 || status.getScore() < 0) {
            throw new NullPointerException("Can't create a status with a null or negative score");
        } else if (status.getCreationDate() == null) {
            throw new NullPointerException("Can't create a status with a null creation date");
        } else if (status.getCreationDate().isAfter(LocalDate.now())) {
            throw new NullPointerException("Can't create a status with a date in the future");
        } else {
            statusRepository.save(status);
        }
    }

    public double calculateScore(CovidStatsDTO covidStats, VaccineDataDTO vaccineStats) {
        if (covidStats == null  || vaccineStats == null)
            throw new NullPointerException("No data found for country provided");
        Status status = new Status();

        double totalPopulation = covidStats.getPopulation();
        /**
         * vaccineStats returns the total num of doses rolled out in a country(to-date)
         * so assuming a double dose vaccine policy for each country the returned num is divided by 2
         * therefore, administeredVax returns an approximate number of vaccines administered in a country
         */
        double administeredVax = vaccineStats.getTimeline().fields().next().getValue().asDouble() /2;

            return  (administeredVax/totalPopulation) * 100;
    }

    public String calculateStatusReport(double percentVaccinated) {

        if (percentVaccinated > 80) {
            return "Safe to travel";
        } else if (percentVaccinated > 40 && percentVaccinated < 80) {
            return "Proceed with caution";
        } else {
            return "Not safe to travel";
        }
    }
}
