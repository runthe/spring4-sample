package com.soo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author KHS
 */
@Data
@EqualsAndHashCode(of = {"name", "password"})
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int number;

    private String name;

    private String password;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
