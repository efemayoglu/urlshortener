package tapu.urlshortener.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tapu.urlshortener.business.abstracts.LoginService;
import tapu.urlshortener.core.utilities.results.ErrorDataResult;
import tapu.urlshortener.entities.concretes.User;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService userService){
        this.loginService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user){
        return ResponseEntity.ok(this.loginService
                .getByUsernameAndPassword(user.getUsername(), user.getPassword()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError:exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<>(validationErrors,"Validation Errors..");
    }

}
