package com.example.jwtst.repository.jpa;

import com.example.jwtst.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> getByUsername(String name);
}
