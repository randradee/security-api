package com.randradee.studies.security.repositories;

import com.randradee.studies.security.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    UserDetails findByLogin(String login);
}
