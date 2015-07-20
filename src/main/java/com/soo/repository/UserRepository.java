package com.soo.repository;

import com.soo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author KHS
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
