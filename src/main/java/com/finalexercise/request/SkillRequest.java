package com.finalexercise.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SkillRequest {
	
	private String description;
	
	private int duration;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate lastUsed;
	
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
	
	
}
