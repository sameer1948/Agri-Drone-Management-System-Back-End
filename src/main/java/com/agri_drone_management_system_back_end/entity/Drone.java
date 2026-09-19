package com.agri_drone_management_system_back_end.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "drone")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @Column(name = "droneid", length = 50, nullable = false)
    private String droneId;

    @Column(name = "dronename", length = 50, nullable = false)
    private String droneName;

    @Column(name = "droneownername", length = 100, nullable = false)
    private String droneOwnerName;

    @Column(name = "droneownerphone", length = 20, nullable = false)
    private String droneOwnerPhone;

}