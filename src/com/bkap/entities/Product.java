package com.bkap.entities;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String proName;
    private Float price;
    private Float salePrice;
    private String description;
    private String categoryID;
    private int status;
    private String createAt;

    private String cateName;

    public Product() {
    }

    public Product(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("Id");
        this.proName = resultSet.getString("ProName");
        this.price = resultSet.getFloat("Price");
        this.salePrice = resultSet.getFloat("SalePrice");
        this.description = resultSet.getString("Description");
        this.categoryID = resultSet.getString("CategoryID");
        this.status = resultSet.getInt("Status");
        this.createAt = resultSet.getString("CreatedAt");
        this.cateName = resultSet.getString("CateName");
    }

    public Product(String id, String proName, Float price, Float salePrice, String description, String categoryID, int status, String createAt) {
        this.id = id;
        this.proName = proName;
        this.price = price;
        this.salePrice = salePrice;
        this.description = description;
        this.categoryID = categoryID;
        this.status = status;
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public String getCateName() {
        return cateName;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", proName='" + proName + '\'' +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", description='" + description + '\'' +
                ", categoryID='" + categoryID + '\'' +
                ", status=" + status +
                ", createAt='" + createAt + '\'' +
                ", cateName='" + cateName + '\'' +
                '}';
    }
}
