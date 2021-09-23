package com.todo;


import java.io.File;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();

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
				
			case "ls":
				TodoUtil.listAll(l);
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
		} while (!quit);
		TodoUtil.saveList(l, "TodoList.txt");
	}
}
