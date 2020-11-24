package com.example.demo.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "documents")
public class Documents {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinTable(
            name = "document_keyword",
            joinColumns = @JoinColumn(name = "doc_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "id")
    )
    private List<Keywords> keywords;

    public Documents() {
    }

    public Documents(int id, Date date, String name) {
        this.id = id;
        this.date = date;
        this.name = name;
    }
    public Documents(int id ,Date date,String name,Keywords... keywords){
        this.id = id;
        this.date = date;
        this.name = name;
        this.keywords = Arrays.asList(keywords);
        this.keywords.forEach(x -> x.getDocuments().add(this));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Keywords> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keywords> keywords) {
        this.keywords = keywords;
    }
}
