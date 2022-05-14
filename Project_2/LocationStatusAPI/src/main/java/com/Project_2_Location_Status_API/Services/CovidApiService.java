package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CovidApiService {

    private static final String GET_COVID_API = "https://disease.sh/v3/covid-19/countries/";
    private static final String GET_COVID_VACCINE_INFO = "https://disease.sh/v3/covid-19/vaccine/coverage/countries/";


//    @Autowired
//    private RestTemplate covidApiRestTemplate;

    public ResponseEntity<CovidStatsDTO> getAllDataByCountry(String country){

        RestTemplate covidApiRestTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

//        System.out.println(result.getBody().getCountry());

        return covidApiRestTemplate.exchange(GET_COVID_API + country, HttpMethod.GET,entity, CovidStatsDTO.class);
    }

    public Integer calculateStatus (ResponseEntity<CovidStatsDTO> response) {

//        Can access the response to get whatever information you need for the calculations (check below for example)
//        String cases = response.getBody().getCases();

        return 70;
    }

    public ResponseEntity<String> getAllVaccineDataByCountry(String country) {

        RestTemplate covidApiRestTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return covidApiRestTemplate.exchange(GET_COVID_VACCINE_INFO + country + "?lastdays=1&fullData=false", HttpMethod.GET, entity, String.class);

    }


}
