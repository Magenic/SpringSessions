package com.robertw.EmployeeSkillsTracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Skill extends BaseEntity {
    private String title;
    private String description;
    private Date lastUsed;
    private long duration;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    @JsonIgnore
    public Employee getEmployee() {
        return employee;
    }

    @JsonProperty(value = "employee_id")
    public long getEmployeeId() {
        return this.employee.getId();
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
