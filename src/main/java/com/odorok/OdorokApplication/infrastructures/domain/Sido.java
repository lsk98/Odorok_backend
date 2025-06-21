package com.odorok.OdorokApplication.infrastructures.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "sidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sido {
    @Id
    @Column(name = "code")
    private Integer code;

    @Column(name= "name")
    private String name;
}
