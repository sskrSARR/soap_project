package com.m1gl.soap.entities;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    private Long id;
    private String className;
    private String description;
    private Sector sector;
}
