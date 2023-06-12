package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("")
    public String index(Model model) {
        //list of employers in the database
        model.addAttribute("title", "All Employers");
        model.addAttribute("Employers", employerRepository.findAll());
        //use template employers/index
        return "employers";

    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Employer");
            model.addAttribute(new Employer());
            return "employers/add";
        }

        employerRepository.save(newEmployer);
        return "employers/add";
    }

    //Add Employer posts to database, error on /employers/add

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        //replace optemployer = null with .findById() and right argument
        //to look for given employer object from data layer
//        Optional optEmployer = null;
//        if (optEmployer.isPresent()) {
//            Employer employer = (Employer) optEmployer.get();
//            model.addAttribute("employer", employer);

        /*displayViewEmployer will be in charge of rendering a page to view the contents of an individual employer object.
        It will make use of that employer object’s id field to grab the correct information from employerRepository.
        optEmployer is currently initialized to null. Replace this using the .findById() method with the right argument to look for the given employer object from the data layer.*/

        Optional<Employer> result = employerRepository.findById(employerId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Employer iD " + employerId);
        } else {
            Employer employer = result.get();
            model.addAttribute("title", employer.getId());
            model.addAttribute("employer", employer);
        }
            return "view/{employerId}";
        }
    }

