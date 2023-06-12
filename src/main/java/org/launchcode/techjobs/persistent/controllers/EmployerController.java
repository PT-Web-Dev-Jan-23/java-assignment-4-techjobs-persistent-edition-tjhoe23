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
        return "employers/index";

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
        return "redirect:";
    }

    //Add Employer posts to database, error on /employers/add

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional<Employer> result = employerRepository.findById(employerId);

        if (result.isPresent()) {
            Employer employer = result.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        }
            return "redirect:";
        }
    }

