package com.ajlan.railwayticketbookingbackend.controller;

import com.ajlan.railwayticketbookingbackend.dto.request.UserRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.CoachResponseDto;
import com.ajlan.railwayticketbookingbackend.dto.response.UserResponseDto;
import com.ajlan.railwayticketbookingbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerUser")
    public UserResponseDto postUser(
            @Valid
            @RequestBody
            UserRequestDto dto
    ) {
        return userService.saveNewUser(dto);
    }


    @GetMapping("/getUser")
    public List<UserResponseDto> getAllUser() {
        return userService.getAllTheUsers();
    }

    @GetMapping("/getUser/{id}")
    public UserResponseDto getAUser(
            @PathVariable
            Long id
    ) {
        return userService.getAUser(id);
    }



    @PutMapping("/updateUser/{id}")
    public UserResponseDto updateUser(
            @PathVariable
            Long id,

            @Valid
            @RequestBody
            UserRequestDto dto

    ) {
        return userService.updateTheUser(id, dto);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id
            ) {
        Boolean isDeleted = userService.deleteTheUser(id);
        if(isDeleted)
            return new ResponseEntity<>(
                    "Account Deleted Success fully",
                    HttpStatus.OK
                    );

        return new ResponseEntity<>(
                "Couldn't delete the account",
                HttpStatus.NOT_ACCEPTABLE
        );
    }


}
