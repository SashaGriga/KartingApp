package com.grigeliracing.karting.repositories;

import com.grigeliracing.karting.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriversRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByNickname(String nickname);
}
