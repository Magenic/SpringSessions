package com.robertw.EmployeeSkillsTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Employee extends BaseEntity {
    private String firstName;
    private String lastName;
    private String role;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    private List<Skill> skills;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonIgnore
    public List<Skill> getSkills() {
        return skills;
    }

    @JsonProperty("skill_ids")
    public List<Long> getSkillIds() {
        return this.skills.stream()
                .map(Skill::getId)
                .collect(Collectors.toList());
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
