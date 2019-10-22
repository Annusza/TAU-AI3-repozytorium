package com.annusza.tau.lab.myBookApp.domain;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class TheTime {

	private LocalDateTime createRowTime;
	private LocalDateTime updateRowTime;
	private LocalDateTime readRowTime;
	
	public LocalDateTime getCreateRowTime() {
	
		return createRowTime;
	}
	
	public void setCreateRowTime(LocalDateTime createRowTime) {
	
		this.createRowTime = createRowTime;
	}
	
	public LocalDateTime getUpdateRowTime() {
	
		return updateRowTime;
	}
	
	public void setUpdateRowTime(LocalDateTime updateRowTime) {
	
		this.updateRowTime = updateRowTime;
	}
	
	public LocalDateTime getReadRowTime() {
	
		return readRowTime;
	}
	
	public void setReadRowTime(LocalDateTime readRowTime) {
	
		this.readRowTime = readRowTime;
	}
	
	
	


	

}
