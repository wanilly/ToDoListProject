package com.todo.dao;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;


import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList { // list는 array 리스트로 되어있음...
	private List<TodoItem> list;  // 하나의 객체를 가지고 있음...
	
	
	
	public void stamps(TodoItem t) {
		list.add(t);
		//return stamps;
	}
	public ArrayList<TodoItem> completedList() {
		return new ArrayList<TodoItem>(list);
	}
	
	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}
	

	public void addItem(TodoItem t) {
		list.add(t);
	}
	
	//cate따로 추가.
	public void addCateItem(TodoItem t) {
		list.add(t);
	}
	
	
	public void deleteItem(TodoItem t) {
		int index = list.indexOf(t);
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
		int index = 1;
		int len = 0;
		//System.out.println("\n" + "[모든 항목 정렬]\n");
		len = list.size();
		System.out.println("[전체 목록], 총 " + len + " 개");
		for (TodoItem myitem : list) {
		//	System.out.println(myitem.getTitle() + myitem.getDesc());
			System.out.print(index+". "+ myitem.toString());
			index ++;
			
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