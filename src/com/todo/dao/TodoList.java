package com.todo.dao;

import java.util.*;


import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList { // list는 array 리스트로 되어있음...
	private List<TodoItem> list;  // 하나의 객체를 가지고 있음...

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		//System.out.println("\n" + "[모든 항목 정렬]\n");
		System.out.println("[전체 목록]");
		for (TodoItem myitem : list) {
		//	System.out.println(myitem.getTitle() + myitem.getDesc());
			System.out.print(myitem.toString());
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) { // 객체가 몇 번째 있는지 알아
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) { // 입력할 때 중복이 있는지 확인하여 중복이면 true...
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
