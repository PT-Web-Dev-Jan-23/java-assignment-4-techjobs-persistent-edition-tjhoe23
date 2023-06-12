package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("")
    public String index(Model model) {
        //list of employers in the database
        model.addAttribute("title", "All Skills");
        model.addAttribute("Skills", skillRepository.findAll());
        //use template employers/index
        return "skills/index";

    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Skill");
            return "skills/add";
        }

        skillRepository.save(newSkill);
        return "redirect:";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        //replace optemployer = null with .findById() and right argument
        //to look for given employer object from data layer
//        Optional optEmployer = null;
//        if (optEmployer.isPresent()) {
//            Employer employer = (Employer) optEmployer.get();
//            model.addAttribute("employer", employer);

        /*displayViewEmployer will be in charge of rendering a page to view the contents of an individual employer object.
        It will make use of that employer object’s id field to grab the correct information from employerRepository.
        optEmployer is currently initialized to null. Replace this using the .findById() method with the right argument to look for the given employer object from the data layer.*/

        Optional<Skill> result = skillRepository.findById(skillId);

        if (result.isPresent()) {
            Skill skill = result.get();
            model.addAttribute("skill", skill);
        }
        return "view/{skillId}";
    }
}


