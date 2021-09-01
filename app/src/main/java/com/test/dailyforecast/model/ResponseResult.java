package com.test.dailyforecast.model;

public class ResponseResult {
    private String cod;
    private String message;
    private String cnt = null;
    private java.util.List<com.test.dailyforecast.model.List> list = null;
    private City city = null;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.test.dailyforecast.model.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.test.dailyforecast.model.List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
