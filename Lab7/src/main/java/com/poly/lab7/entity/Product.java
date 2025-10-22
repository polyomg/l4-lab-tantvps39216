package com.poly.Lab7.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "categoryid")
    Category category;

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}