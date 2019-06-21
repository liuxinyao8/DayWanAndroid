package com.example.daywanandroid.sql;

import com.example.daywanandroid.app.MyApplication;
import com.example.daywanandroid.dao.DaoMaster;
import com.example.daywanandroid.dao.StudentDao;


import java.util.List;

public class DBsql {
    private static DBsql dBsql;
    private final StudentDao dao;

    private DBsql(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.app, "Student.db");
        dao= new DaoMaster(devOpenHelper.getReadableDb()).newSession().getStudentDao();

    }

    public static DBsql getdBsql() {
        if (dBsql==null){
            synchronized (DBsql.class){
                if (dBsql==null){
                    dBsql=new DBsql();
                }
            }
        }
        return dBsql;
    }
    public void insert (Student s){
        dao.insertOrReplaceInTx(s);
    }
    public void delete(Student student){
        dao.delete(student);
    }
    public List<Student> querry(){
        List<Student> list = dao.queryBuilder().list();
        return list;
    }

}
