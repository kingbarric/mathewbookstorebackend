package com.mathew.bookstore.mathewbookstore.model;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class Books {

    @Id
    @Basic(optional = false)
    private String isbn;
    private String author;
    private String name;
    private String description;
    //400.00
    private BigDecimal price;
    @Lob
    private String imageFile;
    private String title;
    private String category;
    private String quantity;
    @Transient
    private BigDecimal lowPrice;
    @Transient
    private BigDecimal highPrice;
}
