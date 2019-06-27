package com.example.oauthserver;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Credentials implements UserDetails {
    private User user;

    public Credentials(User user) {
        this.user = user;
        WebAuthorities.authenticated();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return WebAuthorities.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}

class WebAuthorities {
    static List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    static {


        grantedAuthorities.add(authenticated());
        GrantedAuthority high_risk = new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return "HIGH_RISK";
            }
        };
        grantedAuthorities.add(high_risk);
        GrantedAuthority locked = new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return "LOCKED";
            }
        };
        grantedAuthorities.add(locked);
        GrantedAuthority forgotUsername = new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return "FORGOT";
            }
        };
        grantedAuthorities.add(forgotUsername);
        GrantedAuthority reset = new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return "RESET";
            }
        };
        grantedAuthorities.add(reset);

    }

    protected static GrantedAuthority authenticated() {
        GrantedAuthority authenticated = new SimpleGrantedAuthority("AUTHENTICATED");
        return authenticated;
    }

    public static List<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
}
