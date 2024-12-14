package com.wxy.springbackend.controller;

import com.wxy.springbackend.model.TripSearch;
import com.wxy.springbackend.service.TripSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TripSearchController {
    private TripSearchService tripSearchService;

    public TripSearchController(TripSearchService tripSearchService) {
        this.tripSearchService = tripSearchService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<TripSearch>> getAllTrip(@RequestParam String depart_station,
                                                       @RequestParam String arrival_station,
                                                       @RequestParam String datetime){
        List<TripSearch> trips = tripSearchService.getAllTrip(depart_station, arrival_station, datetime);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/api")
    public ResponseEntity<List<TripSearch>> ShowTrip(@RequestParam String pathid,
                                                     @RequestParam String depart_station,
                                                     @RequestParam String arrival_station,
                                                     @RequestParam String datetime){


    }
}
