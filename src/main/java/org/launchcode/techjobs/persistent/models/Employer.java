package org.launchcode.techjobs.persistent.models;

import jdk.jfr.Enabled;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotBlank
    @NotNull
    @Size(min = 3, max = 51, message = "Location must be between 3 and 51 characters")
    private String location;

    @OneToMany
    @JoinColumn (name = "employer_id")
    private final List<Job> jobs = new ArrayList<>();

    public Employer(String location) {
        super();
        this.location = location;
    }

    public Employer() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
