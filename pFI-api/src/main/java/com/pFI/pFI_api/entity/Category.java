package com.pFI.pFI_api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "colorCode", nullable = false, length = 7)
    private String colorCode;

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(name = updated_at)
    private LocalDateTime updated_at;

}
