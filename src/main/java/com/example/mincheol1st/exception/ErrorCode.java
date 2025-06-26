package com.example.mincheol1st.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    POST_NOT_FOUND(404, "게시글이 존재하지 않습니다."),
    COMMENT_NOT_FOUNT(404, "댓글이 없습니다.");

    private final int status;
    private final String message;
}
