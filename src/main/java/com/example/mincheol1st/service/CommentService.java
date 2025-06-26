package com.example.mincheol1st.service;

import com.example.mincheol1st.repository.entity.CommentsEntity;
import com.example.mincheol1st.repository.entity.PostEntity;
import com.example.mincheol1st.repository.jparepository.CommentJpaRepository;
import com.example.mincheol1st.repository.jparepository.PostJpaRepository;
import com.example.mincheol1st.web.dto.commnets.CommentRequestDto;
import com.example.mincheol1st.web.dto.commnets.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentJpaRepository commentJpaRepository;
    private final PostJpaRepository postJpaRepository;

    @Transactional
    public void createComment(CommentRequestDto commentRequestDto) {
        PostEntity post = postJpaRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        CommentsEntity comment = CommentsEntity.builder()
                .post(post)
                .comment(commentRequestDto.getComment())
                .author(commentRequestDto.getAuthor())
                .build();

        commentJpaRepository.save(comment);
    }

    @Transactional
    public void updateComment(Integer id, CommentRequestDto commentRequestDto) {
        CommentsEntity comment = commentJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));

        comment.update(commentRequestDto.getComment());

    }

    @Transactional
    public void deleteComment(Integer PostId) {
        commentJpaRepository.deleteById(PostId);
    }

    public List<CommentResponseDto> findByPostId(Integer postId) {
        List<CommentsEntity> comments = commentJpaRepository.findByPostId(postId);
        return comments.stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
