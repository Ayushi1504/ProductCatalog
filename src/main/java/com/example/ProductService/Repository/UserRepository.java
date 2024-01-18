package com.example.ProductService.Repository;

import com.example.ProductService.Model.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    @Query("select us from User us where email=:email")
    Optional<User> findByEmail(String email);
}
