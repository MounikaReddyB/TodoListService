package com.todo.handler.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import com.todo.handler.ToDoHandler;
import com.todo.models.ToDoList;
import com.todo.models.ToDoListMapper;

public class ToDoHandlerTest {
	
	@Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ToDoHandler toDoHandler;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<ToDoList> expectedToDoLists = new ArrayList<>();
        expectedToDoLists.add(new ToDoList(1, "Task 1", false));
        expectedToDoLists.add(new ToDoList(2, "Task 2", true));

        when(jdbcTemplate.query("SELECT * FROM todolist", new ToDoListMapper())).thenReturn(expectedToDoLists);

        List<ToDoList> actualToDoLists = toDoHandler.findAll();

        assertEquals(expectedToDoLists, actualToDoLists);
    }

    @Test
    public void testFindById() {
        int id = 1;
        ToDoList expectedToDoList = new ToDoList(id, "Task 1", false);

        when(jdbcTemplate.queryForObject("SELECT * FROM todolist where id = ?", new ToDoListMapper(), id))
                .thenReturn(expectedToDoList);

        Optional<ToDoList> actualToDoList = toDoHandler.findById(id);

        assertEquals(Optional.of(expectedToDoList), actualToDoList);
    }

    @Test
    public void testSave() {
        ToDoList toDoList = new ToDoList(1, "Task 1", false);
        int expectedAffectedRows = 1;

        when(jdbcTemplate.update("INSERT INTO todolist(ID,Description,Status) VALUES(?,?,?)",
                toDoList.getId(), toDoList.getDescription(), toDoList.isStatus())).thenReturn(expectedAffectedRows);

        int actualAffectedRows = toDoHandler.save(toDoList);

        assertEquals(expectedAffectedRows, actualAffectedRows);
    }

    @Test
    public void testUpdate() {
        ToDoList toDoList = new ToDoList(1, "Task 1", false);
        int expectedAffectedRows = 1;

        when(jdbcTemplate.update("UPDATE todolist SET ID=?,Description=?,Status=? WHERE id=?",
                toDoList.getId(), toDoList.getDescription(), toDoList.isStatus(), toDoList.getId()))
                .thenReturn(expectedAffectedRows);

        int actualAffectedRows = toDoHandler.update(toDoList);

        assertEquals(expectedAffectedRows, actualAffectedRows);
    }

    @Test
    public void testDelete() {
        int id = 1;
        int expectedAffectedRows = 1;

        when(jdbcTemplate.update("DELETE FROM todolist WHERE id=?", id)).thenReturn(expectedAffectedRows);

        int actualAffectedRows = toDoHandler.delete(id);

        assertEquals(expectedAffectedRows, actualAffectedRows);
    }
}


