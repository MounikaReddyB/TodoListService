package com.todo.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.todo.api.ToDoListService;
import com.todo.models.ToDoList;
import com.todo.models.ToDoListMapper;

@Configuration
public class ToDoHandler implements ToDoListService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String INSERT_TODO_QUERY = "INSERT INTO todolist(ID,Description,Status) VALUES(?,?,?)";
	private final String GET_TODO_QUERY = "SELECT * FROM todolist";
	private final String GET_TODO_BY_ID_QUERY = "SELECT * FROM todolist where id = ?";
	private final String UPDATE_TODO_QUERY = "UPDATE todolist SET ID=?,Description=?,Status=? WHERE id=?";
	private final String DELETE_TODO_QUERY = "DELETE FROM todolist WHERE id=?";
	
	
	
	
	@Override
	public List<ToDoList> findAll() {
		return jdbcTemplate.query(GET_TODO_QUERY, new ToDoListMapper());
	}

	@Override
	public Optional<ToDoList> findById(int id) {
		return Optional.of(jdbcTemplate.queryForObject(GET_TODO_BY_ID_QUERY, new ToDoListMapper(), new Object[] {id}));
	}
	

	@Override
	public int save(ToDoList todo) {		
		return jdbcTemplate.update(INSERT_TODO_QUERY, new Object[] { todo.getId() , todo.getDescription(), todo.isStatus() });
		
	}

	@Override
	public int update(ToDoList todo) {
		return jdbcTemplate.update(UPDATE_TODO_QUERY, new Object[] { todo.getId(),todo.getDescription(),todo.isStatus() });
	}

	@Override
	public int delete(int id) {
		return jdbcTemplate.update(DELETE_TODO_QUERY, new Object[] { id });
		
	}
	
	

}
