package com.todo.api;

import java.util.List;
import java.util.Optional;

import com.todo.models.ToDoList;

public interface ToDoListService {

	List<ToDoList> findAll();

	Optional<ToDoList> findById(int id);

	int save(ToDoList todo);

	int delete(int id);

	int update(ToDoList todo);

}
