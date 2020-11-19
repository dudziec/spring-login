package com.example.emailsender.security;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findByConfirmationToken(String confirmationToken);
}
