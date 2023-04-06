package com.education.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import model.enum_.NotificationType;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class Notification extends BaseEntity {
    @Column(name = "notification_type")
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @OneToOne(mappedBy = "notification")
    private Employee employee ;
}
