package com.switchfully.codecoach.repository;

import com.switchfully.codecoach.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    List<User> getByIsCoach(boolean isCoach);
}
