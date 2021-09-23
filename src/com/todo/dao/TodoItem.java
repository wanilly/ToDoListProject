package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem { // 멤버함수
    private String title;
    private String desc;
    private String current_date;  // current_date을 string 변경 (스트링)
    

    // construct 생성자 
    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;  
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");  // import를 해주어야 함... 
        this.current_date = f.format(new Date());  // 생성자에서 SimpleDateFormat을 사용하여 문자열로 저
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
   
    // 오버라이딩 화면상에 객체를 출력하기 위함...
    @Override
    public String toString() {
    	return "[" + title + "] " + desc + " - " + current_date + "\n";  // 문자열...
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + current_date + "\n";
    }
}

