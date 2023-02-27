package ru.lenivtsev.market.auth.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.lenivtsev.market.auth.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String role_customer);
}
