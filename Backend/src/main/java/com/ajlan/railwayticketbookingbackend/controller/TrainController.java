package com.ajlan.railwayticketbookingbackend.controller;

import com.ajlan.railwayticketbookingbackend.dto.request.TrainRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.TrainResponseDto;
import com.ajlan.railwayticketbookingbackend.service.StationService;
import com.ajlan.railwayticketbookingbackend.service.TrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/train")
public class TrainController<active> {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;

    }

    @PostMapping("/addTrain")
    public TrainResponseDto addTrain(@RequestBody TrainRequestDto dto) {
        return trainService.addTheTrain(dto);
    }

    @GetMapping("/findTrain/{id}")
    public TrainResponseDto findTrainById(@RequestParam Integer id) {
        return trainService.getTrainById(id);
    }

    @PutMapping("/updateTrain/{id}")
    public TrainResponseDto updateTrain(
            @PathVariable Integer id,
            @RequestBody TrainRequestDto dto
    ) {
        return trainService.updateTrainById(id, dto);
    }

    @PutMapping("/updateTrain/{id}/active")
    public  TrainResponseDto updateTrainActiveStatus(
            @PathVariable Integer id,
            @RequestParam boolean active

    ) {
        return trainService.updateTrainById(id, active);
    }

    @DeleteMapping("/deleteTrain/{id}")
    public ResponseEntity<String> deleteTrainById(
            @PathVariable Integer id
    ) {
        if (trainService.deleteTrainById(id)) {
            return new ResponseEntity<>("Train deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Train deletion failed",HttpStatus.NOT_FOUND);
    }
}

