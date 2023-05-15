package com.grigeliracing.karting.util;

import com.grigeliracing.karting.models.Driver;
import com.grigeliracing.karting.services.DriversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class DriverValidator implements Validator {

    private final DriversService driversService;

    @Autowired
    public DriverValidator(DriversService driversService) {
        this.driversService = driversService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Driver.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Driver person = (Driver) o;

        if (driversService.getDriverByNickname(person.getNickname()).isPresent())
            errors.rejectValue("nickname", "", "Пилот с таким никнеймом уже существует");
    }
}
