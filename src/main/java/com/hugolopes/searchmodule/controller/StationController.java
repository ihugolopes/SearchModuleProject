package com.hugolopes.searchmodule.controller;

import com.hugolopes.searchmodule.model.Station;
import com.hugolopes.searchmodule.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("station")
public class StationController {

    @Autowired
    private StationService service;

    @GetMapping("v1/search")
    public ResponseEntity<Page<Station>> searchWithPageable(@RequestParam("name") String name,
                                @RequestParam(value = "page", required = false,  defaultValue = "0") int page,
                                @RequestParam(value = "size",required = false, defaultValue = "10") int size) {

        Page<Station> results = service.findStationsByNamePageable(name, page, size);

        if(results.isEmpty()){
            ResponseEntity.ok(results);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("v2/search")
    ResponseEntity<List<Station>> searchLuceneWithHibernate(@RequestParam(value = "name") String name, @RequestParam(value = "size", required = false,  defaultValue = "20") int size) {

        try {
            List<Station> results = service.findStationsByNameSearch(name.toLowerCase(), size);

            if(!results.isEmpty()){
                return ResponseEntity.ok(results);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception ex){
            return  ResponseEntity.badRequest().build();
        }

    }


}
