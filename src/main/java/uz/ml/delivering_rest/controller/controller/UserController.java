package uz.ml.delivering_rest.controller.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uz.ml.delivering_rest.controller.AbstractController;
import uz.ml.delivering_rest.dto.user.UserLoginDTO;
import uz.ml.delivering_rest.dto.user.UserRegisterDTO;
import uz.ml.delivering_rest.service.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user/*")
public class UserController extends AbstractController<UserService> {
    public UserController(UserService service) {
        super(service);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@Valid @RequestBody UserLoginDTO dto) {
        HttpEntity<UserLoginDTO> entity = new HttpEntity<>(dto);
        ResponseEntity<Object> exchange = new RestTemplate().exchange("http://localhost:8081/api/auth/login", HttpMethod.POST, entity, Object.class);
        return exchange.getBody();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO dto) {
        service.register(dto);
        return new ResponseEntity<>("Successfully Registered", HttpStatus.CREATED);
    }
}
