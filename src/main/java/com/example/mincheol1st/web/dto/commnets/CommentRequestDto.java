package com.example.mincheol1st.web.dto.commnets;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {

    private Integer id;
    private Integer postId;
    private String comment;
    private String author;

}
