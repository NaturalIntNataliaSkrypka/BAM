package com.naturalint.entity;

import javax.persistence.*;

/**
 * Created by yka on 12/10/2017.
 */

@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", length = 6, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
