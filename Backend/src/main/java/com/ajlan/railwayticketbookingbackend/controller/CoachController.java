package com.ajlan.railwayticketbookingbackend.controller;

import com.ajlan.railwayticketbookingbackend.dto.request.CoachRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.CoachResponseDto;
import com.ajlan.railwayticketbookingbackend.service.CoachService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
@RequiredArgsConstructor
public class CoachController {

    private final CoachService coachService;

    @PostMapping
    public ResponseEntity<CoachResponseDto> addCoach(
            @Valid @RequestBody CoachRequestDto requestDto
    ) {

        CoachResponseDto response =
                coachService.addCoach(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/updateCoach/{coachId}")
    public ResponseEntity<CoachResponseDto> updateCoach(
            @PathVariable Integer coachId,
            @Valid @RequestBody CoachRequestDto requestDto
    ) {

        CoachResponseDto response =
                coachService.updateCoach(
                        coachId,
                        requestDto
                );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteCoach/{coachId}")
    public ResponseEntity<Void> removeCoach(
            @PathVariable Integer coachId
    ) {

        coachService.removeCoach(coachId);

        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/{trainId}")
//    public List<CoachResponseDto> getAllCoachesOfATrain(
//            @PathVariable Integer trainId
//    ) {
//
//
//    }
}