package com.pFI.pFI_api.repository;

import com.pFI.pFI_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}