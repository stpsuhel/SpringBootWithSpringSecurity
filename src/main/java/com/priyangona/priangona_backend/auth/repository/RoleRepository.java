package com.priyangona.priangona_backend.auth.repository;

import com.priyangona.priangona_backend.auth.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
