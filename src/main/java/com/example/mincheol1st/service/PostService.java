package com.example.mincheol1st.service;

import com.example.mincheol1st.repository.entity.PostEntity;
import com.example.mincheol1st.repository.jparepository.PostJpaRepository;
import com.example.mincheol1st.repository.jparepository.UserJpaRepository;
import com.example.mincheol1st.web.dto.post.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserJpaRepository userJpaRepository;
    private final PostJpaRepository postJpaRepository;

    @Transactional
    public void createPost(PostRequestDto requestDto) {
        PostEntity post = PostEntity.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(requestDto.getAuthor())
                .build();

        postJpaRepository.save(post);
    }

    @Transactional
    public void updatePost(Integer id, PostRequestDto requestDto) {
        PostEntity post = postJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        post.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public void deletePost(Integer id) {
        PostEntity post = postJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
    }
}
