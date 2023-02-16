package com.anymindgroup.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "additional_fields_to_save")
@Data
public class Field {
    @Id
    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "description")
    private String description;
}
