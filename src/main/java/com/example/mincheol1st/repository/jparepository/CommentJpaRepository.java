package com.example.mincheol1st.repository.jparepository;

import com.example.mincheol1st.repository.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentJpaRepository extends JpaRepository<CommentsEntity,Integer> {

    List<CommentsEntity> findByPostId(Integer postId);
}
