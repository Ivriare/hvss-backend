package web;

import dto.user.UserAuthenticationData;
import dto.user.UserDataTransfer;
import dto.user.UserRegisterData;
import dto.user.UserStatus;
import entities.user.HvssUser;
import entities.user.HvssUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.UserService;

@Controller
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<UserDataTransfer> authenticateUser(@RequestBody UserAuthenticationData userAuthenticationData){
        UserDataTransfer userDataTransfer = userService.authenticateUser(userAuthenticationData);
        if(userDataTransfer.getUserStatus() == UserStatus.AUTHENTICATED){
            return new ResponseEntity<>(userDataTransfer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userDataTransfer, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDataTransfer> registerUser(@RequestBody UserRegisterData userRegisterData){
        UserDataTransfer userDataTransfer = userService.registerUser(userRegisterData);
        if(userDataTransfer.getUserStatus() == UserStatus.CREATED) {
            return new ResponseEntity<>(userDataTransfer, HttpStatus.CREATED);
        } else if (userDataTransfer.getUserStatus() == UserStatus.EXISTS){
            return new ResponseEntity<>(userDataTransfer, HttpStatus.CONFLICT);
        }
        //this line should never run
        return new ResponseEntity<>(userDataTransfer, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/auth/refresh")
    public ResponseEntity<UserDataTransfer> refreshUserToken(@RequestBody UserDataTransfer userDataTransfer){
        UserDataTransfer returnUserDataTransfer = userService.refreshAuthToken(userDataTransfer);

        return new ResponseEntity<>(returnUserDataTransfer, HttpStatus.OK);
    }


    @PostMapping(value = "/auth/logout")
    public ResponseEntity<UserDataTransfer> logoutUser(@RequestBody UserDataTransfer userDataTransfer){
        UserDataTransfer returnUserDataTransfer = userService.logoutUser(userDataTransfer);
        return new ResponseEntity<>(returnUserDataTransfer, HttpStatus.OK);
    }


}
