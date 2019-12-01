package com.example.mobileprograming_project;

//데이터 하나의 정보를 가지는 객체를 생성해주는 클래스
public class Item {
    String SIZE;
    String NAME;
    String DATE;
    String LINK;
    String REMARK;
    Item(){}
    Item(String name,String date,String size,String link,String remark) {
        this.NAME=name;
        this.DATE=date;
        this.SIZE=size;
        this.LINK=link;
        this.REMARK=remark;
    }
    public void setName(String name){this.NAME=name;}
    public void setLink(String link){this.LINK=link;}
    public void setDate(String date){this.DATE=date;}
    public void setSize(String size){this.SIZE=size;}
    public void setRemark(String remark){this.REMARK=remark;}

}
