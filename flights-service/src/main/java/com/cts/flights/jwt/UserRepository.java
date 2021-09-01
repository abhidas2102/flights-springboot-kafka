package com.cts.flights.jwt;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
 
public interface UserRepository extends CrudRepository<User, Long> {
 
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public Optional<User> getUserByUsername(@Param("username") String username);
}