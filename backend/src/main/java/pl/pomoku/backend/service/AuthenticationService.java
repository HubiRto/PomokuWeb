package pl.pomoku.backend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pomoku.backend.dto.request.LoginRequest;
import pl.pomoku.backend.dto.request.RegisterRequest;
import pl.pomoku.backend.dto.response.AuthenticationResponse;
import pl.pomoku.backend.entity.Role;
import pl.pomoku.backend.entity.User;
import pl.pomoku.backend.exception.AppException;
import pl.pomoku.backend.jwt.JwtService;
import pl.pomoku.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public ResponseEntity<?> register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(encoder.encode(request.password()))
                .role(Role.USER)
                .build();
        repository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Correctly create account");
    }

    public AuthenticationResponse login(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.email(),
                    request.password()
                )
        );
        var user = repository.findByEmail(request.email()).orElseThrow(() ->
                new AppException("Incorrect email or password", HttpStatus.BAD_REQUEST));
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
