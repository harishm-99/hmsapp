package com.hmsapp.payload;

import java.util.Date;

public class ErrorDetails {

    private String msg;
    private String request;
    private Date date;

    public ErrorDetails (String msg, String request, Date date){
        this.msg = msg;
        this.request = request;
        this.date = date;
    }

    public String getMsg(){
        return msg;
    }

    public String getRequest(){
        return request;
    }

    public Date getDate(){
        return date;
    }
}
