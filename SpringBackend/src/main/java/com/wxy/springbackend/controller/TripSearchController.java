package com.wxy.springbackend.controller;

import com.wxy.springbackend.model.TripSearch;
import com.wxy.springbackend.service.TripSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TripSearchController {
    private TripSearchService tripSearchService;

    public TripSearchController(TripSearchService tripSearchService) {
        this.tripSearchService = tripSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<TripSearch>> getAllTrip(@RequestParam String depart_station,
                                                       @RequestParam String arrival_station,
                                                       @RequestParam String datetime){
        List<TripSearch> trips = tripSearchService.getAllTrip(depart_station, arrival_station, datetime);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }
}
