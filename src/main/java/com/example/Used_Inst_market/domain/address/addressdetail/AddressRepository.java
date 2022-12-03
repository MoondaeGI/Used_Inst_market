package com.example.Used_Inst_market.domain.address.addressdetail;

import com.example.Used_Inst_market.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUser(User user);
}
