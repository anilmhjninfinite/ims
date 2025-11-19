package org.infinite.ims.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roleName;

    @Size(min = 1, max = 100)
    private String description;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Role(String roleName) {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
        this.description = "System generate role "+ roleName;
        this.roleName = roleName;
    }
}
