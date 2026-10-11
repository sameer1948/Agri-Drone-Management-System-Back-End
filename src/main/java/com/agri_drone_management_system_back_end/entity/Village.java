package com.agri_drone_management_system_back_end.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "village")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "village_id")
    private Long villageId;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

}