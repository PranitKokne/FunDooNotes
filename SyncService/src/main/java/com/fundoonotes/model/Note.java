package com.fundoonotes.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Note {

	private long id;
	private String title;
	private String description;
	private String colour;
	private Date createdDate;
	private Date lastModifiedDate;
	private boolean isArchieved;
	private boolean isTrash;
	private boolean isPinned;
	private String operation;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public boolean isArchieved() {
		return isArchieved;
	}

	public void setArchieved(boolean isArchieved) {
		this.isArchieved = isArchieved;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", description=" + description + ", colour=" + colour
				+ ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate + ", isArchieved="
				+ isArchieved + ", isTrash=" + isTrash + ", isPinned=" + isPinned + "]";
	}

}
