package com.javamicroservice.userinfo.repository;

import com.javamicroservice.userinfo.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {
    java.util.Optional<UserInformation> findByUserName(String userName);
}
