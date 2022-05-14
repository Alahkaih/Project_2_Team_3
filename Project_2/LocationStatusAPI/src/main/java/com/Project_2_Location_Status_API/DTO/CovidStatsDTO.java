package com.Project_2_Location_Status_API.DTO;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "CovidStatsDTO")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CovidStatsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", columnDefinition = "AUTO_INCREMENT")
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "cases")
    private String cases;

    @Column(name = "todayCases")
    private LocalDate todayCases;

}
