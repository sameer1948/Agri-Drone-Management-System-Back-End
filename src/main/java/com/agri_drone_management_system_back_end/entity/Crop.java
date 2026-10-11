package com.agri_drone_management_system_back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "crop")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long cropId;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

}