package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role implements Serializable {    

    private static final long serialVersionUID = 1L;
    @Id    
    @Column(name="rol")
    private String rol;
}