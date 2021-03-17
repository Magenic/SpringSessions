package com.magenic.springboot.finals.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Skills extends  AuditTable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "skills_id")
    private long id;

    private String description;
    private int duration;
    private LocalDate lastUsed;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDate lastUsed) {
        this.lastUsed = lastUsed;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
