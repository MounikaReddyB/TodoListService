package com.todo.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.models.ToDoList;

@RestController
@RequestMapping("/toDoListService")
public class ToDoController {	
	
	 
	private final ToDoHandler toDoHandler;
	
	
    @Autowired
    public  ToDoController(ToDoHandler toDoHandler) {
        this.toDoHandler = toDoHandler;
    }
    /**
     * Create a new toDoList.
     *
     * @param toDoList the toDoList to create
     * @return the ResponseEntity with status 200 (OK) 
     */
    @PostMapping("/createToDoList")
    public ResponseEntity<?> saveToDoList(@RequestBody ToDoList toDoList) {
        toDoHandler.save(toDoList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    
	    /**
	     * Get all toDolist.
	     *
	     * @return the ResponseEntity with status 200 (OK) and with body of the list of toDolist
	     */
	    @GetMapping("/getToDoList")
	    public ResponseEntity<?> getAllToDoList() {
	    	List<ToDoList> toDolist = toDoHandler.findAll();
			return new ResponseEntity<>(toDolist, HttpStatus.OK);
	        
	    }
	 
	    /**
	     * Get a toDolist by ID.
	     *
	     * @param id the ID of the toDolist to get
	     * @return the ResponseEntity with status 200 (OK) and with body of the toDolist
	     */
	    @GetMapping("/getToDoListbyID/{id}")	     
	    public ResponseEntity<Optional<ToDoList>> getToDoListById(@PathVariable int id) {
	    	 Optional<ToDoList> toDoList = toDoHandler.findById(id);
	        return ResponseEntity.ok(toDoList);
	    }
	 
	    /**
	     * Update a toDolist by ID.
	     *
	     * @param id the ID of the toDolist to update
	     * @param toDolist the updated toDolist
	     * @return the ResponseEntity with status 200 (OK) 
	     */
	    @PutMapping("/updateToDoList/{id}")
	    public ResponseEntity<ToDoList> updateToDoList( @RequestBody ToDoList toDoList) {
	         toDoHandler.update( toDoList);
	        return new  ResponseEntity<>(HttpStatus.OK);
	    }
	    
	    
	 
	    /**
	     * Delete a toDolist by ID.
	     *
	     * @param id the ID of the toDolist to delete
	     * @return the ResponseEntity with status 200 (OK) and with body of the message "ToDolist deleted successfully"
	     */
	    @DeleteMapping("/deleteToDoList/{id}")
	    public ResponseEntity<?> deleteToDoList(@PathVariable int id) {
	        toDoHandler.delete(id);
	       // return new ResponseEntity<>(HttpStatus.OK);
	       return ResponseEntity.ok("ToDolist deleted successfully");
	    }
	}
