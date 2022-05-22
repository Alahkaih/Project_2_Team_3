package com.Project_2_Location_Search_API.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MapServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MapService mapService;

    @Test
    public void shouldGetLocationInfo(){

        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);

        ResponseEntity res = mapService.getLocationInfo("India", "json");
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
    }

    @Test
    public void shouldGetByPostalCode(){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);

        ResponseEntity res = mapService.getByPostalCode("10001", "US", "json");
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
    }
}
