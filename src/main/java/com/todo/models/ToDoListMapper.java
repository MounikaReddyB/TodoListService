package com.todo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ToDoListMapper  implements RowMapper<ToDoList> {  
    
    @Override 
    public ToDoList mapRow(ResultSet rs, int rowNum) throws SQLException {   
        
        ToDoList toDoList = new ToDoList();
        toDoList.setId(rs.getInt("ID"));
        toDoList.setDescription(rs.getString("Description"));
        toDoList.setStatus(rs.getBoolean("Status"));
      
        return toDoList; 
    } 
}