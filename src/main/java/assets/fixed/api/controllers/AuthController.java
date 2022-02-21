package assets.fixed.api.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import assets.fixed.api.models.User;
import assets.fixed.api.security.jwt.JwtProvider;
import assets.fixed.api.services.interfaces.IUserService;
import assets.fixed.api.utilities.enums.RoleNameEnum;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthController {
    public final static Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody User userParam){
        HttpStatus httpStatus;
        String message;
        if(userParam.getUsername() == null || userParam.getUsername().isBlank() || userParam.getUsername().isEmpty() ){
            return new ResponseEntity<>("Falta el usuario", HttpStatus.BAD_REQUEST);
        }
        if(userParam.getPassword() == null || userParam.getPassword().isBlank() || userParam.getPassword().isEmpty()){
            return new ResponseEntity<>("Falta la contraseña", HttpStatus.BAD_REQUEST);
        }
        try {
            Optional<User> user = userService.findById(userParam.getUsername());
            if(user.isPresent()){
                if(userParam.getUsername().equals(user.get().getUsername())){
                    if(userParam.getPassword().equals(user.get().getPassword())){
                        LOGGER.debug("Loggeado exitosamente");
                        message = jwtProvider.generateToken(user.get());
                        httpStatus = HttpStatus.OK;
                    } else {
                        LOGGER.debug("Contraseña incorrecta", userParam.getPassword());
                        message = "Usuario o contraseña incorrecto(s)";
                        httpStatus = HttpStatus.BAD_REQUEST;
                    }
                } else {
                    LOGGER.debug("Usuario incorrecta", userParam.getUsername());
                    message = "Usuario o contraseña incorrecto(s)";
                    httpStatus = HttpStatus.BAD_REQUEST;
                }
            } else {
                LOGGER.debug("El usuario {} no existe", userParam.getUsername());
                message = "Usuario o contraseña incorrecto(s)";
                httpStatus = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception error) {
            LOGGER.debug("Error: {}", error.getMessage());
            message = "Error en el servicio";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Object>(message, httpStatus);
    }

    @PostMapping(value =  "new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> newUser(@RequestBody User userParam){
        String message;
        HttpStatus httpStatus;
        if(userParam.getUsername() == null || userParam.getUsername().isBlank() || userParam.getUsername().isEmpty()){
            return new ResponseEntity<>("Falta el usuario", HttpStatus.BAD_REQUEST);
        }
        if(userParam.getPassword() == null || userParam.getPassword().isBlank() || userParam.getPassword().isEmpty()){
            return new ResponseEntity<>("Falta la contraseña", HttpStatus.BAD_REQUEST);
        }
        User user = new User(userParam.getUsername(),userParam.getPassword(), RoleNameEnum.ROLE_ADMIN.toString());
        try {
            userService.saveUser(user);
            message = "Usuario almacenado exitosamente";
            httpStatus = HttpStatus.OK;
        } catch (Exception error) {
            message = "Error: " + error.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(message, httpStatus);
    }
}
