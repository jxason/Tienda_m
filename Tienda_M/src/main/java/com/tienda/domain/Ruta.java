package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="ruta")
public class Ruta implements Serializable {    
    private static final long serialVersionUID = 1l;    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ruta")
    private Long idRuta;        
    private String patron;
    private String rolName;        
}