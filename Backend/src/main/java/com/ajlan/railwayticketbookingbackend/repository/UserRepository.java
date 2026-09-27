package com.ajlan.railwayticketbookingbackend.repository;

import com.ajlan.railwayticketbookingbackend.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>  {

    User findUserByUserId(Long id);
    Boolean deleteUserByUserId (Long id);


    User findUserByEmail(@NotBlank(message = "email cannot be null") @Email(message = "please enter a valid email") String email);
}
