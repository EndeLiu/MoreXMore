package com.more.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "account")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "record_type")
    int type;

    @Column(name = "category")
    String category;

    @Column(name = "subcategory")
    String subCategory;

    @Column(name = "record_desc")
    String desc;

    @Column(name = "record_value")
    double value;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "record_date")
    Date date;

    @Column(name = "pay")
    String pay;

    @Column(name = "userid")
    String userid;

    public Account(int type, String category, String subCategory, String desc, double value, Date date, String pay, String userid) {
        this.type = type;
        this.category = category;
        this.subCategory = subCategory;
        this.desc = desc;
        this.value = value;
        this.date = date;
        this.pay = pay;
        this.userid = userid;
    }

    public Account() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
