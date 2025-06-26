package com.example.mincheol1st.service;

import com.example.mincheol1st.repository.entity.LikeEntity;
import com.example.mincheol1st.repository.entity.PostEntity;
import com.example.mincheol1st.repository.entity.UserEntity;
import com.example.mincheol1st.repository.jparepository.LikeJpaRepository;
import com.example.mincheol1st.repository.jparepository.PostJpaRepository;
import com.example.mincheol1st.repository.jparepository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeJpaRepository likeJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public void createLike(Integer postId, String email) {
        PostEntity post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        UserEntity user = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        likeJpaRepository.findByUserAndPost(user, post).ifPresentOrElse((like) -> {
            throw new IllegalStateException("이미 좋아요를 추가 했습니다.");
        },
                () -> {
            likeJpaRepository.save(LikeEntity.builder()
                            .user(user)
                            .post(post)
                            .build());
                });
    }

    @Transactional
    public void deleteLike(Integer postId, String email) {
        PostEntity post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        UserEntity user = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        LikeEntity like = likeJpaRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new IllegalArgumentException("좋아요 기록이 없습니다."));

        likeJpaRepository.delete(like);
    }
}
