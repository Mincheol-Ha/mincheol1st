package com.example.mincheol1st.service;

import com.example.mincheol1st.repository.jparepository.PostJpaRepository;
import com.example.mincheol1st.web.dto.post.PostRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {


    @Mock
    private PostJpaRepository postJpaRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void 게시글_작성_성공() {
        PostRequestDto dto = new PostRequestDto("제목", "내용", "mincheol");

        postService.createPost(dto);

        verify(postJpaRepository, times(1)).save(any());
    }
}