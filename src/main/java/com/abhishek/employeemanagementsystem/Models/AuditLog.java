package com.abhishek.employeemanagementsystem.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timeStamp;
    private EntityType entityType;
    private Long entityId; // ID of the entity being modified
    private String fieldName; // Name of the field being modified (e.g., projectManager, employees)
    private String oldValue; // Previous value of the field
    private String newValue; // New value of the field
    private ActionType actionType; // Type of change (e.g., Update, Delete, Create)
    private Long userId; // ID of the user responsible for the change
}
