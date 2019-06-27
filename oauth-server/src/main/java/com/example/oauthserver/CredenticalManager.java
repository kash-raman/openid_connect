package com.example.oauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class CredenticalManager implements UserDetailsManager, UserDetailsPasswordService {
    @Autowired
    UserRepository userDAO;

    @Override
    public UserDetails updatePassword(UserDetails userDetails, String s) {

        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        userDAO.save(((Credentials) userDetails).getUser());
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        userDAO.save(((Credentials) userDetails).getUser());

    }

    @Override
    public void deleteUser(String s) {
        throw new RuntimeException();
    }

    @Override
    public void changePassword(String s, String s1) {
        throw new RuntimeException();
    }

    @Override
    public boolean userExists(String s) {
        return userDAO.existsByUserName(s);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new Credentials(user);
    }
}
