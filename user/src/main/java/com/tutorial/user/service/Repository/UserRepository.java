package com.tutorial.user.service.Repository;

import com.tutorial.user.service.Entity._User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<_User, Integer> {
}
