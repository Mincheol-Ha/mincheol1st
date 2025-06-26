package com.example.mincheol1st.repository.jparepository;

import com.example.mincheol1st.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<PostEntity,Integer> {
    Optional<PostEntity> findById(Integer id);

}
