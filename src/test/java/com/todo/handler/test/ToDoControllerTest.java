package com.todo.handler.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.todo.handler.ToDoController;
import com.todo.handler.ToDoHandler;
import com.todo.models.ToDoList;

public class ToDoControllerTest {

    @Mock
    private ToDoHandler toDoHandler;

    @InjectMocks
    private ToDoController toDoController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveToDoList() {
        ToDoList toDoList = new ToDoList();
        toDoList.setId(1);
        toDoList.setStatus(false);
        toDoList.setDescription("Task 1");
        ResponseEntity<?> responseEntity = toDoController.saveToDoList(toDoList);
        
        verify(toDoHandler).save(toDoList);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllToDoList() {
        List<ToDoList> expectedToDoLists = new ArrayList<>();
      
        
        expectedToDoLists.add(new ToDoList(1, "Task 1", false));
        expectedToDoLists.add(new ToDoList(2, "Task 2", true));

        when(toDoHandler.findAll()).thenReturn(expectedToDoLists);

        ResponseEntity<?> responseEntity = toDoController.getAllToDoList();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedToDoLists, responseEntity.getBody());
    }

    @Test
    public void testGetToDoListById() {
        int id = 1;
        ToDoList expectedToDoList = new ToDoList(id, "Task 1", false);
        Optional<ToDoList> optionalToDoList = Optional.of(expectedToDoList);

        when(toDoHandler.findById(id)).thenReturn(optionalToDoList);

        ResponseEntity<Optional<ToDoList>> responseEntity = toDoController.getToDoListById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(optionalToDoList, responseEntity.getBody());
    }

    @Test
    public void testUpdateToDoList() {
        ToDoList toDoList = new ToDoList(1, "Task 1", false);
        ResponseEntity<ToDoList> responseEntity = toDoController.updateToDoList(toDoList);

        verify(toDoHandler).update(toDoList);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteToDoList() {
        int id = 1;
        ResponseEntity<?> responseEntity = toDoController.deleteToDoList(id);

        verify(toDoHandler).delete(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("ToDolist deleted successfully", responseEntity.getBody());
    }
}