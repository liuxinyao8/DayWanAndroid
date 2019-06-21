package com.example.daywanandroid.sql;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    @Property
    private String autho;
    @Property
    private String time;
    @Property
    private String title;
    @Property
    private String typename;
    @Generated(hash = 1891368882)
    public Student(Long id, String autho, String time, String title,
            String typename) {
        this.id = id;
        this.autho = autho;
        this.time = time;
        this.title = title;
        this.typename = typename;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAutho() {
        return this.autho;
    }
    public void setAutho(String autho) {
        this.autho = autho;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTypename() {
        return this.typename;
    }
    public void setTypename(String typename) {
        this.typename = typename;
    }
  
}
