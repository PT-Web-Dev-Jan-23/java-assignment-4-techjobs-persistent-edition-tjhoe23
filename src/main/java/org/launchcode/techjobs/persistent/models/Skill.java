package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

    @NotBlank
    @Size(min=3, max=51, message="Please enter a description between 3 and 51 characters")
    public String description;

    public Skill(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy="skills")
    private final List<Job> jobs = new ArrayList<>();

    public Skill() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }


}