package com.todo;


import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

import com.todo.dao.TodoDueDate;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoSystem;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws ParseException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		TodoDueDate list = new TodoDueDate();
		//TodoItem t= new TodoItem(0, null, null);
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "TodoList.txt");
		Menu.displaymenu();
		do {
			//Menu.displaymenu();
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "com":
				//String key = sc.nextLine().trim();
				TodoSystem.complete(l);
				break;
			
			case "ls_com":
				// 완료 항목 리스트에 저장...
				
				TodoSystem.isComplete(l);
				break;	
				
			case "ls":
				TodoUtil.listAll(l);
				break;
			
			// 카테고리별 항목 , 중복되지 않도록 카테고리 종류 개수와 카테고리 가져오기
			case "ls_cate":
				TodoUtil.cateList(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("\n제목 순으로 정렬하였습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("\n제목 역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("\n날짜 순으로 정렬하였습니다.");
				isList = true;
				break;
			
			// 날짜 거꾸로 순으로 
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("\n날짜 역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			// find function
			case "find":
				// TodoUtil.find 만들어서 구현해볼까? 
				TodoUtil.find(l);
				break;
				
			case "end":
				//TodoSystem.checkSubject(l);
				TodoSystem.stamps(l);
			
				TodoSystem.isdueDate(l);
				System.out.println("오늘의 할일을 내일로 미루지 마세요!");
				break;	
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				System.out.println("\n프로그램을 종료합니다.\n");
				quit = true;
				break;
			
			default:
				System.out.println("\n정확한 명령어를 입력하시오 (도움말 - help)");
				break;
			}
			if(isList) l.listAll();
			//if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "TodoList.txt");
	}
}


