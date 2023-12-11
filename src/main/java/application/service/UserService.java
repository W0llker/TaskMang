package application.service;

import api.dto.user.UserRequest;
import api.dto.user.UserResponse;
import api.service.CrudService;
import api.service.UserApi;
import application.entity.Client;
import application.mapper.ClientMapper;
import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<Client, UserRequest, UserResponse> implements UserDetailsService, UserApi {
    private final UserRepository repository;
    private final ClientMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, ClientMapper mapper, PasswordEncoder passwordEncoder) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse add(UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return mapper.createResponse(repository.save(mapper.createEntity(userRequest)));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = repository.findByEmail(email);
        if (client == null)
            throw new UsernameNotFoundException("Пользователь с email " + email + " не найден");
        return client;
    }

    @Override
    public UserResponse findUserEmail(String email) {
        return mapper.createResponse(repository.findByEmail(email));
    }
}
