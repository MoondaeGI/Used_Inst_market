package com.example.Used_Inst_market.model.domain.address;

import com.example.Used_Inst_market.model.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUser(User user);
}
