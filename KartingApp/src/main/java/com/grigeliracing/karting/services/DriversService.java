package com.grigeliracing.karting.services;

import com.grigeliracing.karting.models.Driver;
import com.grigeliracing.karting.repositories.DriversRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DriversService {

    private final DriversRepository driversRepository;

    @Autowired
    public DriversService(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    public List<Driver> findAll() {
        return driversRepository.findAll();
    }

    public Driver findOne(int id) {
        Optional<Driver> foundDriver = driversRepository.findById(id);
        return foundDriver.orElse(null);
    }

    @Transactional
    public void save(Driver driver) {
        driversRepository.save(driver);
    }

    @Transactional
    public void update(int id, Driver updatedDriver) {
        updatedDriver.setId(id);
        driversRepository.save(updatedDriver);
    }

    @Transactional
    public void delete(int id) {
        driversRepository.deleteById(id);
    }

    public Optional<Driver> getDriverByNickname(String nickname) {
        return driversRepository.findByNickname(nickname);
    }

}
