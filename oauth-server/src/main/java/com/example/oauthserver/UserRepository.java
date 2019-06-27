package com.example.oauthserver;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserNameAndPassword(String userName, String Password);

    User findByUserName(String username);

    User findByEmail(String email);

    boolean existsByUserName(String username);
}
