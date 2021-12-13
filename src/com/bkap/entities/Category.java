package com.bkap.entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String cateName;
    private int status;
    private int parentID;

    public Category() {
    }

    public Category(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("Id");
        this.cateName = resultSet.getString("CateName");
        this.status = resultSet.getInt("Status");
        this.parentID = resultSet.getInt("parentID");
    }


    public Category(int id, String cateName, int status, int parentID) {
        this.id = id;
        this.cateName = cateName;
        this.status = status;
        this.parentID = parentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", cateName='" + cateName + '\'' +
                ", Status='" + status + '\'' +
                ", parentID=" + parentID +
                '}';
    }
}
