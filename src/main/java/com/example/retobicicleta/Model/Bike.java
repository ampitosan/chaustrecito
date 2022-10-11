package com.example.retobicicleta.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="bike")
public class Bike implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String brand;
    @Column (name = "years")

    private Integer year;
    private String description;


    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties("bikes")
    private Category category;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "bike")
    @JsonIgnoreProperties({"bike", "client"})
    public List<Message> messages;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "bike")
    @JsonIgnoreProperties({"bike", "messages"})
    public List<Reservation> reservations;

    public Bike() {
    }

    public Bike(String brand, String name, String description, Integer year, Category category, List<Message> messages, List<Reservation> reservations) {
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.year = year;
        this.category = category;
        this.messages = messages;
        this.reservations = reservations;
    }

    public Bike(Integer id, String brand, String name, String description, Integer year, Category category, List<Message> messages, List<Reservation> reservations) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.year = year;
        this.category = category;
        this.messages = messages;
        this.reservations = reservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}