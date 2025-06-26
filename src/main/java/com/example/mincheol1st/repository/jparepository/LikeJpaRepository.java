package com.example.mincheol1st.repository.jparepository;

import com.example.mincheol1st.repository.entity.LikeEntity;
import com.example.mincheol1st.repository.entity.PostEntity;
import com.example.mincheol1st.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeJpaRepository extends JpaRepository<LikeEntity, Long> {

    Optional<LikeEntity> findByUserAndPost(UserEntity user, PostEntity post);
    int countByPost(PostEntity post);
}
