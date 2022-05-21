package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
// import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CovidApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CovidApiService covidApiService;

    @Test
    public void shouldGetALlVaccineDataByCountrySuccessfully(){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode timelineNode = mapper.createObjectNode();
        ((ObjectNode) timelineNode).put("5/21/22", 84952660);

        VaccineDataDTO vaccineDataDTO = new VaccineDataDTO("Canada", timelineNode);

        ResponseEntity<VaccineDataDTO> responseEntity = new ResponseEntity<VaccineDataDTO>(vaccineDataDTO, HttpStatus.ACCEPTED);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<VaccineDataDTO>>any()))
                .thenReturn(responseEntity);

        ResponseEntity res = covidApiService.getAllVaccineDataByCountry("Canada");
        Assertions.assertEquals(responseEntity, res);
    }

}