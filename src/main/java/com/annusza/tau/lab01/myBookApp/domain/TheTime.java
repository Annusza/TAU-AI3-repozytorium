package com.annusza.tau.lab01.myBookApp.domain;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class TheTime {

	private LocalDateTime createRowTime;
	private LocalDateTime updateRowTime;
	private LocalDateTime readRowTime;

	public LocalDateTime getCreateRowDateTime() {

		return createRowTime;
	}

	public void setCreateRowTime(LocalDateTime createRowTime) {

		this.createRowTime = createRowTime;
	}

	public LocalDateTime getUpdateRowDateTime() {

		return updateRowTime;
	}

	public void setUpdateRowTime(LocalDateTime updateRowTime) {

		this.updateRowTime = updateRowTime;
	}

	public LocalDateTime getReadRowDateTime() {

		return readRowTime;
	}

	public void setReadRowTime(LocalDateTime readRowTime) {

		this.readRowTime = readRowTime;
	}

}
