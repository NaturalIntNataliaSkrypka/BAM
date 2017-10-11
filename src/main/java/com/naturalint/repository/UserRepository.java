package com.naturalint.repository;

import com.naturalint.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by skn on 10/11/2017.
 */
public interface UserRepository  extends JpaRepository<User, String> {
    public User findByEmail(String email) ;
}
