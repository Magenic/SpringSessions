package com.example.groupsession.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Skill extends BaseEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
	private int duration;
    private LocalDate lastUsed;
	private long employeeId;
    
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
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
}
