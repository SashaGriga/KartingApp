package com.grigeliracing.karting.controllers;

import com.grigeliracing.karting.models.Driver;
import com.grigeliracing.karting.services.DriversService;
import com.grigeliracing.karting.util.DriverValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drivers")
public class DriversController {

    private final DriversService driversService;
    private final DriverValidator driverValidator;

    @Autowired
    public DriversController(DriversService driversService, DriverValidator driverValidator) {
        this.driversService = driversService;
        this.driverValidator = driverValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("drivers", driversService.findAll());
        return "drivers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("driver", driversService.findOne(id));
        return "drivers/show";
    }

    @GetMapping("/new")
    public String newDriver(@ModelAttribute("driver") Driver driver) {
        return "drivers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("driver") @Valid Driver driver,
                         BindingResult bindingResult) {
        driverValidator.validate(driver, bindingResult);

        if (bindingResult.hasErrors())
            return "drivers/new";

        driversService.save(driver);
        return "redirect:/drivers";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("driver", driversService.findOne(id));
        return "drivers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Driver driver, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "drivers/edit";

        driversService.update(id, driver);
        return "redirect:/drivers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        driversService.delete(id);
        return "redirect:/drivers";
    }
}
