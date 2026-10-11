package com.agri_drone_management_system_back_end.entity;

import com.agri_drone_management_system_back_end.model.DroneStatus;
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
    @Column(name = "drone_id", length = 50, nullable = false)
    private String droneId;

    @Column(name = "drone_name", length = 50, nullable = false)
    private String droneName;

    @Column(name = "drone_owner_name", length = 100, nullable = false)
    private String droneOwnerName;

    @Column(name = "drone_owner_phone", length = 20, nullable = false)
    private String droneOwnerPhone;

    @Column(name = "drone_status", length = 20, nullable = false)
    private DroneStatus droneStatus = DroneStatus.ACTIVE;

}