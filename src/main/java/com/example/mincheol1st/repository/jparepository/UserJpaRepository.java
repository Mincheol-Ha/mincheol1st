package com.example.mincheol1st.repository.jparepository;

import com.example.mincheol1st.repository.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);

}
