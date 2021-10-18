package com.todo.dao;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem { // 멤버함수
	private int id;
	private int is_completed;
    private String title;
    private String desc;
    private String current_date;  // current_date을 string 변경 (스트링)
    private String cate;
    private String due_date;
    private String completed_date;
    private int stamps; //
    private boolean checkDate; // 경과시간을 알수 있나?, 3
    private String checkDay; ///
    private String checkWeek;
    private String dueCheck; // 마감일 얼마나 남았는지 알려주기?
    
    
    public TodoItem(int stamps, String checkDay, String checkWeek) {
    	Date d = new Date();
    	SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
    	this.stamps = stamps;
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(d);
    	cal.add(Calendar.HOUR, 72);
    	checkDay = f.format(cal.getTime());
    	
    	
    	Calendar cl = Calendar.getInstance();
    	cl.setTime(d);
    	cl.add(Calendar.DATE, 7);
    	checkWeek = f.format(cl.getTime());
    	
    	this.checkDay = checkDay;
    	this.checkWeek = checkWeek;
    	
    }
    
    public void setCheckDay(String checkDay) {
    	this.checkDay = checkDay;
    	
    }
    
    public String getCheckDay() {
    	return checkDay;
    }
    
    public void setCheckWeek(String checkWeek) {
    	this.checkWeek = checkWeek;
    }
    public String getCheckWeek() {
    	return checkWeek;
    }
    // 마감일에 대해서 계산을 해야할까? 아니면, 완료한날짜>?
    
    public TodoItem(int is_completed, String completed_date, String cate, String title, String due_date, String desc) {
   
    	this.is_completed = is_completed;
    	
    	SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
    	this.completed_date = f.format(new Date());
    	this.cate = cate;
    	this.due_date = due_date;
    	this.title = title;
    	this.desc = desc;
    }
    
    // 
    public TodoItem(String cate) {
    	this.cate = cate;
    }
    
    // construct 생성자 
    public TodoItem(String cate, String title, String desc, String due_date){
    	this.cate = cate;
        this.title=title;
        this.desc=desc;  
        this.due_date = due_date;
        
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");  // import를 해주어야 함... 
        this.current_date = f.format(new Date());  // 생성자에서 SimpleDateFormat을 사용하여 문자열로 저
        
       
        
    }
    

	// 카테고리 추가함..
    public String getCate() {
    	return cate;
    }
    
    public void setCate(String cate) {
    	this.cate = cate;
    }
    
    // 마감일 추가함..
    public String getDueDate() {
    	return due_date;
    }
    
    public void setDueDate(String due_date) {
    	this.due_date = due_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    
    public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id = id;
    }
    
    //is_completed
    public int getCompleted() {
    	return is_completed;
    }
    
    public void setCompleted(int is_completed) {
    	this.completed_date = completed_date;
    	this.is_completed = is_completed;
    }
    
    public String getCompleted_date() {
    	return current_date;
    }
    
    public void setCompleted_date(String current_date) {
    	this.current_date = current_date;
    }
    
    // 오버라이딩 화면상에 객체를 출력하기 위함...
    @Override
    public String toString() {
    	return id +". "+ "[" + cate + "] " + "<" + title + ">" + " - " + desc + " - " + due_date + " - " + current_date + "\n";  // 문자열...
    }
    
    public String toSaveString() {
    	return cate + "##"+ title + "##" + desc + "##" + due_date + "##"+ current_date + "\n";
    }
    
    
    public String toComString() {
    	return "[" + cate + "] " + "<" + title + ">" + " - " + desc + " - " + due_date + " - " + current_date + "\n";
    }
    
    public String toCompletedString(TodoList l) {
    	completed_date = getCompleted_date();
    	
    	return "[" + cate + "] " + title + " - " + desc + " - " + completed_date + "\n";   
    }
}
