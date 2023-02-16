package com.anymindgroup.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "couriers")
@Data
public class Courier {
    @Id
    @Column(name = "courier_name")
    private String courierName;

    @Column(name = "description")
    private String description;
}
