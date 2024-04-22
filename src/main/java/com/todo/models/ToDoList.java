package com.todo.models;



public class ToDoList {
	public int id;
    public String description;
    public boolean status;
	public ToDoList(int i, String string, boolean b) {
		// TODO Auto-generated constructor stub
	}
	public ToDoList() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ToDoList [id=" + id + ", description=" + description + ", status=" + status + "]";
	}
	
	
    
    

}
