package com.cts.zuul.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cts.zuul.beans.User;
 
public interface UserRepository extends CrudRepository<User, Long> {
 
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public Optional<User> getUserByUsername(@Param("username") String username);
}