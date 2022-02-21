package assets.fixed.api.services.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import assets.fixed.api.entities.EUser;
import assets.fixed.api.models.User;
import assets.fixed.api.repositories.UserRepository;
import assets.fixed.api.services.interfaces.IUserService;
import assets.fixed.api.utilities.Helpers;

@Component
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    private User eUserToUser(final EUser eUser){
        return Helpers.modelMapper().map(eUser, User.class);
    }

    @Override
    public Optional<User> findById(String user) {
        try{
            Optional<EUser> eUser = this.userRepository.findById(user);
            return eUser.map(this::eUserToUser);
        } catch(Exception e) {
            System.out.println("No existe el usuario");
            return null;
        }
    }

    @Override
    public void saveUser(User user) {
        EUser eUser = Helpers.modelMapper().map(user, EUser.class);
        try {
            userRepository.save(eUser);
        }
        catch(Exception e){
            System.out.println("Error al guardar");
        }
        
    }
}
