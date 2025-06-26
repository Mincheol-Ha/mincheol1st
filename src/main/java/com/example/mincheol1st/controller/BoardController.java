package com.example.mincheol1st.controller;

import com.example.mincheol1st.service.CommentService;
import com.example.mincheol1st.service.LikeService;
import com.example.mincheol1st.service.PostService;
import com.example.mincheol1st.service.UserService;
import com.example.mincheol1st.web.dto.commnets.CommentRequestDto;
import com.example.mincheol1st.web.dto.commnets.CommentResponseDto;
import com.example.mincheol1st.web.dto.post.PostRequestDto;
import com.example.mincheol1st.web.dto.user.LoginRequestDto;
import com.example.mincheol1st.web.dto.user.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return ResponseEntity.ok("회원가입 완료");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // 프론트가 토큰을 삭제하면 됨 (서버는 인증 처리 없이 단순 응답)
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PostMapping("/posts")
    public ResponseEntity<String> posts(@RequestBody PostRequestDto postRequestDto) {
    postService.createPost(postRequestDto);
    return ResponseEntity.ok("게시글이 작성되었습니다.");
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable("postId") int postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return   ResponseEntity.ok("글 수정 완료.");
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable("postId") int postId) {
        postService.deletePost(postId);
        return   ResponseEntity.ok("삭제 되었습니다.");
    }

    @PostMapping("/comments")
    public ResponseEntity<String > creatComment(@RequestBody CommentRequestDto  commentRequestDto) {
        commentService.createComment(commentRequestDto);
        return  ResponseEntity.ok("댓글 등록완료.");
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(id, commentRequestDto);
        return ResponseEntity.ok("댓글 수정 완료");
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Integer id) {
     List<CommentResponseDto> comments = commentService.findByPostId(id);
        log.info("댓글 ID:" + id + " 조회 요청");
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<String> createLike(@PathVariable Integer postId, @RequestBody String email) {
        likeService.createLike(postId, email);
        log.info("좋아요 요청 이메일: {}", email);
        return ResponseEntity.ok("좋아요가 등록되었습니다.👍");
    }

    @DeleteMapping("/posts/{postId}/like")
    public ResponseEntity<String> deleteLike(@PathVariable Integer postId, @RequestBody String email) {
        likeService.deleteLike(postId, email);
        log.info("좋아요 취소요청 이메일: {}", email);
        return ResponseEntity.ok("좋아요가 취소되었습니다.❌");
    }
}
