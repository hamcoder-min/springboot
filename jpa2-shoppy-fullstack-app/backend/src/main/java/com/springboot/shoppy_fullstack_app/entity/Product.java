package com.springboot.shoppy_fullstack_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="product")
@Getter @Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String name;
    private long price;
    private String info;
    private double rate;
    private String image;

    @Column(columnDefinition = "JSON")
    private String imgList;

    //Product(1) : (1..N)ProductDetailinfo 엔티티 정의 - 필드값
    //Product(one) to ProductDetailinfo(one) :: pid가 하나씩 있기 때문에 OneToOne 사용. 만약 pid가 겹치는게 여러개라면(1이 2개 이상씩 있다거나...) OneToMany 사용.
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ProductDetailinfo detailinfo;

    //Product(1) : (1..N)ProductQna
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductQna> qna;
}
