package com.ajlan.railwayticketbookingbackend.controller;

import com.ajlan.railwayticketbookingbackend.dto.request.LoginRequestDto;
import com.ajlan.railwayticketbookingbackend.dto.response.LoginResponseDto;
import com.ajlan.railwayticketbookingbackend.dto.response.UserResponseDto;
import com.ajlan.railwayticketbookingbackend.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequestDto requestDto
    ) {
        LoginResponseDto dto = authService.login(requestDto);
        System.out.println("halloooooooo ooo o");
        if(dto != null)
            return ResponseEntity.ok(dto);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid Email or Password");
    }


    @GetMapping("/session")
    public ResponseEntity<?> getSession(HttpSession session) {

        Object userId = session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Not logged in");
        }

        return ResponseEntity.ok().build();
    }
}
