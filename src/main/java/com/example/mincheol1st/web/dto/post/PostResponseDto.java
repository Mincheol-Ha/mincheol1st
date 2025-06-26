package com.example.mincheol1st.web.dto.post;

import com.example.mincheol1st.repository.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static PostResponseDto from(PostEntity post) {
        return PostResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }


}
