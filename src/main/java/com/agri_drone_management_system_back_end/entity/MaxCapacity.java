package com.agri_drone_management_system_back_end.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "max_capacity")
public class MaxCapacity {

    @Id
    private Long id = 1L; // Pin to 1L without GenerationType to avoid detached entity confusion

    @Column(name = "max_acres", nullable = false)
    private Integer maxAcres;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}