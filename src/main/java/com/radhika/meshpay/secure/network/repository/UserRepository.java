package com.radhika.meshpay.secure.network.repository;

import com.radhika.meshpay.secure.network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);


    boolean existsByEmail(String email);
}
