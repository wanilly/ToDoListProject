package com.todo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.todo.dao.TodoDueDate;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;


public class TodoSystem {
	// complete 기능, 완료를 표시하고, 한 주에 몇 개 혹은 하루에 몇개 이상 달성 시 도장을 받게 하고
	// 날짜별 성공한 완료한 개수르르 list에 추가/관리 하여 is_com 기능에서 확인할 수 있도록 한다. 
	// 검색 기능 구현하기... 카테고리, 주제, 내용, 마감일 등 모두 점검하게 할까?
	public static void complete(TodoList l) {
		// 완료면 - 1, 미실시 - 0으로 하게함 아니면, 어떤 항목을 입력 받아 완료하셨습니까?
		String cate = null, title = null , desc = null, due_date = null;
		int is_completed = 0;
		String complete_date = null;
		String checkDay, checkWeek;
		System.out.print("완료한 항목의 키워드를 입력 >> ");
		Scanner sc = new Scanner(System.in);
		String key = sc.next();
	
		for (TodoItem item : l.getList()) {
			
			if (item.getTitle().contains(key) == true ||
					item.getDesc().contains(key) == true || item.getCate().contains(key)) {
				
				System.out.print("완료 항목에 추가하시겠습니까(y/n)? >> ");
				String termCom = sc.next();
				
				if (termCom.equals("y")) {
					System.out.println("아래 항목과 같습니다.");
					System.out.println(item.toString());
					is_completed = 1;
					TodoItem t = new TodoItem(is_completed, complete_date, cate, title, due_date, desc);
					cate = item.getCate();
					desc = item.getDesc();
					title = item.getTitle();
					due_date = item.getDueDate();
					
					item.setCompleted(is_completed);
					//item.getCompleted();
					
					//list.addItem(item);
					//l.addItem(item);
					//list.completedList().add(item);
					
				}
				else if (termCom.equals("n")) {
					System.out.println("추가를 취소합니다.");
					return;
				}
				else {
					System.out.println("잘못된 접근입니다.");
				}
			}  
		}

	}

	
	//날짜별 성공한 완료한 항목 리스트에 저장하기 ...
	// 날짜별로 관리하려면 완료한날짜를 current로 저장하는게 어떻니?
	public static void isComplete(TodoList l) {
		Scanner sc = new Scanner(System.in);
		//System.out.println("");
		//for (String item : l.getComplete()) {
		//	System.out.print(l.toString());
		//	System.out.print("ss");
		//}
		/*
		for (TodoItem item : l.getList()) {
			if (item.getCompleted() == 1) {
				System.out.print(item.toCompletedString(l));
			}
		
		}
		*/
		
		for (TodoItem it : l.completedList()) {
			if (it.getCompleted() == 1) {
				System.out.print(it.toCompletedString(l));
				//System.out.print(it.toString());
			}
		}
	}
	
	//한 주에 몇 개 혹은 하루에 몇개 이상 달성 시 도장을 받게 하고, 리스트 번호와 체크시간 / 한주 개ㅔ
	public static int stamps(TodoList l) { // 완료체크 현재시간 저장을 따로 해야겠죠?
		//TodoItem t = new TodoItem();
		int stamps = 0;
		for (TodoItem t : l.completedList()) {
			if (t.getCompleted() == 1) {
			stamps ++;
			}
		}
		
		System.out.println("현재까지 달성한 도장: "+stamps + "개 ");
		
		return stamps;
		
	}
	
	// 입력한 시간이 오래 지난 (5일 이상 지났는데, 완료처리 하라고)
	// 시간에 따른 경과 리스트 저장, 만약 달성 기준이 부족한 경우 (주마다 몇개 달성하지 못할경우 - 메세지 출력 우리 좀 더 노력해요) 
	public static void checkSubject(TodoList l) {
		
		String checkDay;
		String checkWeek;
		for (TodoItem item : l.getList()) {
			if (item.getCurrent_date().equals(item.getDueDate())) {
				System.out.println("마감일을 확인하세요!!");
			}
			//else if (l.getList)
			//else if (item.getCurrent_date() - item.getDueDate()){
				
				//System.out.print(item.getCheckDay() + item.getCheckWeek());
			//}
		}
	}
	
	public static void isdueDate(TodoList l) throws ParseException {
		int miss_count= 0;
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
	
		//Date date1 = sdformat.parse
		for (TodoItem item : l.getList()) {
			 Date d1 = f.parse(item.getDueDate());
	         Date d2 = f.parse(item.getCurrent_date());
	       
			if(d1.equals(d2)) {
				System.out.println("마감일자를 확인하세요!!");
			}
			else if (d1.after(d2)) {

			}
			
			else if (d1.before(d2)) {
				if(item.getCompleted() == 1) {
					miss_count --;
				}
				miss_count ++;
			}
		}
		System.out.println(miss_count+"개의 항목을 놓치고 있습니다. 매일 리스트를 정리하세요!");
		
	}
	/*
	public Boolean isdueDate(TodoList l) {
		try {
			
			SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
			
			//Date date1 = sdformat.parse
			for (TodoItem item : l.getList()) {
				 Date d1 = f.parse(item.getDueDate());
		         Date d2 = f.parse(item.getCurrent_date());
				if(d1.before(d2)) {
					
				}
				else if (d1.after(d2)) {
					
				}
				else if (d1.equals(d2)) {
					System.out.println("마감일자를 확인하세요!!");
					return true;
				}
			}
		
		} catch(ParseException ex) {
			
		}
		
		return false;
	}
	*/
}
