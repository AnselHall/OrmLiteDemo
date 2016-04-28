package com.ansel.ormlitedemo.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by user on 2016/4/28.
 */

@DatabaseTable(tableName = "tb_user") //表明这是数据库的一张表，表明为：tb_user
public class User {

    //generatedId 表示 id 为主键，且自动生成
    @DatabaseField(generatedId = true)
    private int id;

    //columnName 的值为该字段的数据库中列明
    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "desc")
    private String desc;

    public User() {
    }

    public User(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
