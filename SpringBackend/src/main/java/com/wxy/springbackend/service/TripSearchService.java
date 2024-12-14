package com.wxy.springbackend.service;

import com.wxy.springbackend.model.TripSearch;
import com.wxy.springbackend.repository.TripSearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TripSearchService {
    private final TripSearchRepository tripSearchRepository;
    private final double UNIT_PRICE_A = 225.71;
    private final double UNIT_PRICE_B = 323.14;
    private final double UNIT_PRICE_C = 514.85;

    public TripSearchService(TripSearchRepository tripSearchRepository){
        this.tripSearchRepository = tripSearchRepository;
    }

    public List<TripSearch> getAllTrip(String depart_station, String arrival_station, String datetime){
        List<TripSearch> res = tripSearchRepository.findAll(depart_station, arrival_station, datetime);

        int count = 0;
        boolean spot = false;
        for(TripSearch t : res){
            count = 0;
            spot = false;
            for(String station: t.getStations()){
                if(station.equals(depart_station)){
                    spot = true;
                    count++;
                }else if(station.equals(arrival_station)){
                    count++;
                    t.setPrices_A(count * UNIT_PRICE_A);
                    t.setPrices_B(count * UNIT_PRICE_B);
                    t.setPrices_C(count * UNIT_PRICE_C);
                    break;
                }else if(spot){count++;}
            }

        }

        return res;
    }
}
