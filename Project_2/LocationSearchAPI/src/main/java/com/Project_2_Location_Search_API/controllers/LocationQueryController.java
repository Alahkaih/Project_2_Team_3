package com.Project_2_Location_Search_API.controllers;

import com.Project_2_Location_Search_API.dto.MapWithStatus;
import com.Project_2_Location_Search_API.entities.LocationQuery;
import com.Project_2_Location_Search_API.entities.StatusReport;
import com.Project_2_Location_Search_API.service.LocationQueryService;
import com.Project_2_Location_Search_API.service.MapService;
import lombok.Setter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class LocationQueryController {

    @Setter(onMethod =@__({@Autowired}))
    private LocationQueryService locationQueryService;
    @Setter(onMethod =@__({@Autowired}))
    private MapService mapService;

    @PostMapping("/")
    public void addLocationQuery(@RequestBody LocationQuery locationQuery) {
        LocationQuery locationQueryAdded = locationQueryService.saveSearch(locationQuery);
        if (locationQueryAdded == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error happened when adding a search object");
        }
    }

    @GetMapping("/")
    public List<LocationQuery> getAllLocationQueries() {
        return locationQueryService.getAllSearches();
    }

    @GetMapping("")
    public List<LocationQuery> getAllByFilter(@RequestParam String filter, @RequestParam String filterStr, @RequestParam int minNum) {
        List<LocationQuery> retList = locationQueryService.getAllByFilter(filter, filterStr, minNum);
        return retList;
    }

    @GetMapping("{country}")
    public List<ResponseEntity> getCountryStatusMap(@PathVariable String country){
        StatusReport statusReport =locationQueryService.requestStatusReportByCountry(country);
        String status = statusReport.getStatus();
        ResponseEntity<String> statusResponse = new ResponseEntity<>(status, HttpStatus.OK);
        System.out.println(statusResponse);

        ResponseEntity img = ResponseEntity.ok((mapService.getLocationMap(country,"json")));
        System.out.println(img.getBody());

        MapWithStatus mapWithStatus = new MapWithStatus(img, status);

        List<ResponseEntity> entities = new ArrayList<>();
        entities.add(ResponseEntity.ok(mapWithStatus).getBody().getImg());
        entities.add(statusResponse);
        //return ResponseEntity.ok(mapWithStatus).getBody().getImg();
//        return ResponseEntity.ok(mapWithStatus).getBody().getImg();
//        return ResponseEntity.ok(entities);
        return entities;
    }







}
