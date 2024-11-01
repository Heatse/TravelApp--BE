package com.travel_app.travel.repository;

import java.util.Optional;

import com.travel_app.travel.entity.ERole;
import com.travel_app.travel.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
