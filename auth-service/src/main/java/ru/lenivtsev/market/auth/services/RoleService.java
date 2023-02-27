package ru.lenivtsev.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lenivtsev.market.auth.model.Role;
import ru.lenivtsev.market.auth.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(){
        return roleRepository.findByRoleName("ROLE_CUSTOMER").get();
    }
}
