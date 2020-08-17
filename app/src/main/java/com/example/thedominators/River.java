package com.example.thedominators;

public class River {

    String name;
    String start;
    String end;
    String city;
    String ghat;
    String length;
    String area;
    String imgid;


    public River() {

    }


    public River(String name, String start, String end, String city, String ghat, String length, String area ,String imgid) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.city = city;
        this.ghat = ghat;
        this.length = length;
        this.area = area;
        this.imgid=imgid;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGhat() {
        return ghat;
    }

    public void setGhat(String ghat) {
        this.ghat = ghat;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}




