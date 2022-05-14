package com.Project_2_Location_Status_API.DTO;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CovidStatsDTO {

    private String country;
    private String cases;
    private Integer todayCases;

}
