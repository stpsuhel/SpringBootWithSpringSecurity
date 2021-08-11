package com.priyangona.priangona_backend.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.priyangona.priangona_backend.auth.role.PrimaryRole;
import com.priyangona.priangona_backend.auth.role.Role;
import com.priyangona.priangona_backend.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })

})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 50)
    private String name;

    @Size(min = 3, max = 60)
    private String username;

    @Size(min = 3)
    @JsonIgnore
    private String password;

    @Size(max = 20)
    private String mobile;

    @Size(min = 3, max = 50)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PrimaryRole primaryRole;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    private int status = Status.Active.value;
}
