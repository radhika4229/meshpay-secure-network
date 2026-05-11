package com.radhika.meshpay.secure.network.repository;

import com.radhika.meshpay.secure.network.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
