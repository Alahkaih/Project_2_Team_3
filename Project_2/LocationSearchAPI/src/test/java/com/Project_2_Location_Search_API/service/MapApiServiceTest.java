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
public class MapApiServiceTest {
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private MapService mapService;
//
//    @Test
//    public void shouldFetchRequest(){
//
//        String url = "http://localhost:8081/map/state-info?state=Texas&format=json&countrycodes=us";
//
//
//        ResponseEntity<String> responseEntity = new ResponseEntity<>(url, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<String>>any()))
//                .thenReturn(responseEntity);
//
//        ResponseEntity response = mapService.fetchRequest(url);
//        Assertions.assertEquals(responseEntity.getBody(), response.getBody());
//    }

}
