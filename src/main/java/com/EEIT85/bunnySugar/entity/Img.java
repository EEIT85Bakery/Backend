package com.EEIT85.bunnySugar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "img")
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "img", nullable = true)
    private String img;

    public Img() {
    }

    public Img(Long id, String img) {
        this.id = id;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
