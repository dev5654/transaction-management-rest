package uz.ml.delivering_rest.service.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.config.encryption.PasswordEncoderConfigurer;
import uz.ml.delivering_rest.dto.user.UserRegisterDTO;
import uz.ml.delivering_rest.entity.entity.User;
import uz.ml.delivering_rest.mapper.mapper.UserMapper;
import uz.ml.delivering_rest.repository.repository.UserRepository;
import uz.ml.delivering_rest.service.AbstractService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Objects;

@Service
@Transactional
public class UserService extends AbstractService<UserMapper, UserRepository> implements UserDetailsService {
    public UserService(UserMapper mapper, UserRepository repository) {
        super(mapper, repository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }

    public void register(UserRegisterDTO dto) {
        dto.setPassword(new PasswordEncoderConfigurer().encoder().encode(dto.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        repository.save(user);
    }
}
