package org.infinite.ims.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Length(min = 4, max = 30)
    private String username;

    @Length(min = 8, message = "Password must be at least 8 character")
    private String password;

    @NotNull
    @NotEmpty
    private String firstName;
    private String lastName;

    @Email
    private String email;
    private boolean isAccountExpired;
    private boolean isLocked;
    private boolean isEnabled;
    private boolean isCredentialsExpired;

    @Pattern(regexp="^\\+?[0-9]{10,15}$", message="Invalid phone number")
    private String contact;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime lastLoginDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

}

