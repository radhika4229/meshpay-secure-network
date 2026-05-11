package com.radhika.meshpay.secure.network.security;

import com.radhika.meshpay.secure.network.dto.AuthResponse;
import com.radhika.meshpay.secure.network.dto.LoginRequest;
import com.radhika.meshpay.secure.network.dto.RegisterRequest;
import com.radhika.meshpay.secure.network.entity.User;
import com.radhika.meshpay.secure.network.exception.EmailAlreadyExistsException;
import com.radhika.meshpay.secure.network.exception.InvalidCredentialsException;
import com.radhika.meshpay.secure.network.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .balance(0.0)
                .build();
        userRepository.save(user);
        return new AuthResponse(null,"User registered successfully");
    }
    public AuthResponse login( LoginRequest loginRequest){
        User user=userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()->new InvalidCredentialsException("Invalid email or password"));
         boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(),user.getPassword());
   if(!passwordMatch){
       throw new InvalidCredentialsException("Invalid Password ");
   }
   String token=jwtService.generateToken(user.getEmail());
   return new AuthResponse(token,"Login successful");

    }





}
