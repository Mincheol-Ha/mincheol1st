package com.example.mincheol1st.service;

import com.example.mincheol1st.repository.entity.PostEntity;
import com.example.mincheol1st.repository.entity.UserEntity;
import com.example.mincheol1st.repository.jparepository.UserJpaRepository;
import com.example.mincheol1st.web.dto.post.PostRequestDto;
import com.example.mincheol1st.web.dto.user.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();

        if(userJpaRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signupRequestDto.getPassword());

        UserEntity user = UserEntity.builder()
                .email(email)
                .password(encodedPassword)
                .build();


        userJpaRepository.save(user);

    }

    @Transactional(readOnly = true)
    public void login(String email, String password) {
        // 1. 이메일로 사용자 조회
        UserEntity user = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        // 2. 비밀번호 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. (선택) 로그인 성공 시 토큰 발급 또는 메시지 반환
        System.out.println("로그인 성공");
    }

}
