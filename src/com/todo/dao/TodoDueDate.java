package com.todo.dao;

import java.util.ArrayList;
import java.util.List;

public class TodoDueDate {
	private List<TodoItem> list;
	public void completedList(TodoItem t) {
		//int is_completed;
		list.add(t); 
	}

	
	public void stamps(TodoItem t) {
		list.add(t);
		//return stamps;
	}
	
	public void addItem(TodoItem t) {
		list.add(t);
	}
	
	public TodoDueDate() {
		this.list = new ArrayList<TodoItem>();
	}
	
	
	public ArrayList<TodoItem> completedList() {
		return new ArrayList<TodoItem>(list);
	}
}
