package assets.fixed.api.services.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Service;

import assets.fixed.api.models.User;

@Service("userService")
public interface IUserService {
    public Optional<User> findById(String id);
    public void saveUser(User user);
}
