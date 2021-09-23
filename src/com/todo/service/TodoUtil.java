package com.todo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;
// 프로그램 시작 시 읽기, 종료 시 저장...
// TodoUtil.java 안에 클래스 메소드를 만들어야 함...
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) { 
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[항목 추가]\n" + "주 제 >> ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("\n이미 목록에 있습니다.");
			return;
		}
		System.out.print("내 용 >> "); // 문장 전체를 입력 받아야한다.
		sc.nextLine();
		desc = sc.nextLine(); // trim을 사용하는 이유는 좌우 여백...
		desc = desc.trim();
		
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("\n추가 되었습니다."); 
	
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n" + "[항목 삭제]\n"+ "삭제할 주 제 >> ");
		String title = sc.next();
	
		// 전체리스트 중에 아이템 하나씩 불러와서 그 항목이 일치하는지 점검..
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
				break;
			}
			else if (!l.isDuplicate(title)) {
				System.out.println("\n해당 목록이 없습니다.");
				return;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "[업데이트]\n" + "수정할 주 제 >> ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("\n해당 목록이 없습니다.");
			return;
		}

		System.out.print("새로운 주 제 >> ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) { // 중복 검사를 통해 점검... 
			System.out.println("\n이미 목록에 있습니다.\n");
			return;
		}
		sc.nextLine();
		System.out.print("새로운 내 용 >> "); 
		String new_description = sc.nextLine();
		new_description = new_description.trim(); // trim
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item); // 원래 내용을 지워버리고...
				TodoItem t = new TodoItem(new_title, new_description); // 새로운 내용을 저장하기..
				l.addItem(t); 
				System.out.println("\n목록이 수정되었습니다.");
			}
		}

	}
	// 아이템의 toString 통해서 하나의 목록을 출력...
	public static void listAll(TodoList l) {
		System.out.println();
		System.out.println("[전체 목록]");
	
		for (TodoItem item : l.getList()) {
			//System.out.println("[" + item.getTitle() + "]" + item.getDesc());
			System.out.print(item.toString());
		
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		//File file = new File("/Users/wani/Documents/workspace/TodoList.txt");
		try {
			FileWriter fl = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				fl.write(item.toSaveString());
			}
			fl.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("파일의 내용이 저장되었습니다.");
		
	}
	
	public static void loadList(TodoList l, String filename) {
	//	File f = new File("TodoList.txt");
	//	if (f.isFile()) {
		//	System.out.println("TodoList.txt의 파일이 없습니다.");
	//	}
		int lineCount = 0;
		try {
			File f = new File(filename);
			if (!f.exists()) {
				System.out.println("파일이 존재하지 않습니다.");
				return;
			}
			else {
				BufferedReader bf = new BufferedReader(new FileReader(filename)); 
				String s;
				while((s = bf.readLine()) != null) {
					lineCount ++;
					StringTokenizer st = new StringTokenizer(s, "##");
					String title = st.nextToken();
					String desc = st.nextToken();
					TodoItem t = new TodoItem(title, desc);
					l.addItem(t);
				}
			bf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(lineCount + "개의 항목을 읽었습니다.");
	}

}
