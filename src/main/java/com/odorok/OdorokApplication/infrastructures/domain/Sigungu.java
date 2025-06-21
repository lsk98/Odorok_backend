package com.odorok.OdorokApplication.infrastructures.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "sigungus")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sigungu {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name= "code")
    private String code;

    @Column(name= "name")
    private String name;

    @Column(name= "sido_code")
    private String sidoCode;
}
