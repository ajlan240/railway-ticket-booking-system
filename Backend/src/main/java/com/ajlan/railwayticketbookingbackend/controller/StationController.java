package com.ajlan.railwayticketbookingbackend.controller;

import com.ajlan.railwayticketbookingbackend.dto.request.StationRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.StationResponseDto;
import com.ajlan.railwayticketbookingbackend.entity.Station;
import com.ajlan.railwayticketbookingbackend.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/station")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/addStation")
    public StationResponseDto addStation(
            @RequestBody
            StationRequestDto dto
    ) {
        return stationService.addTheStation(dto);
    }
    @GetMapping("/getStation")
    public List<StationResponseDto> getAllStations() {
        return stationService.getAllStations();
    }

    @GetMapping("/getStation/{id}")
    public ResponseEntity<?> getAStationById(
            @PathVariable
            Integer id
    ) {
        StationResponseDto dto =  stationService.getStationById(id);
        if(dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    @PutMapping("/getStation/{id}")
    public ResponseEntity<?> updateAStationById(
            @PathVariable
            Integer id,
            StationRequestDto dto

    ) {
        StationResponseDto responseDto =  stationService.updateTheStation(id, dto);
        if(responseDto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("couldn't update");
    }

    @DeleteMapping("/deleteStation/{id}")
    public ResponseEntity<String> deleteStationById(
            @PathVariable Integer id
    ) {
        Boolean isDeleted = stationService.deleteTheStation(id);
        if(isDeleted)
            return new ResponseEntity<>(
                    "Station Deleted Success fully",
                    HttpStatus.OK
            );

        return new ResponseEntity<>(
                "Couldn't delete the station",
                HttpStatus.NOT_ACCEPTABLE
        );
    }



}
