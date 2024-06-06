package com.ra.bt_ss2.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean gender;
    private Date birthday;
    private String address;
    private String company;
    private Double salary;
    @ManyToOne
    @JoinColumn(name = "depId", referencedColumnName = "id")
    private Department department;
}
