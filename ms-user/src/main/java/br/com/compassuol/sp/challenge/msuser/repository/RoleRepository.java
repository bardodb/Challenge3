package br.com.compassuol.sp.challenge.msuser.repository;

import br.com.compassuol.sp.challenge.msuser.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
