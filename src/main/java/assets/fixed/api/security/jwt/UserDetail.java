package assets.fixed.api.security.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import assets.fixed.api.models.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetail implements UserDetails{
    private String name;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetail build(User user){
        SimpleGrantedAuthority auth = new SimpleGrantedAuthority(user.getRole());
        List<GrantedAuthority> authority = new ArrayList<>();
        authority.add(auth);
        return new UserDetail(user.getUsername(), user.getPassword(), authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
