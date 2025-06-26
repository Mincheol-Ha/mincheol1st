package com.example.mincheol1st.web.dto.commnets;

import com.example.mincheol1st.repository.entity.CommentsEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private long id;
    private Integer postId;
    private String comment;
    private String author;

    public static CommentResponseDto fromEntity(CommentsEntity entity) {
        return CommentResponseDto.builder()
                .id(entity.getId())
                .postId(entity.getPost().getId())
                .comment(entity.getComment())
                .author(entity.getAuthor())
                .build();
    }

}
