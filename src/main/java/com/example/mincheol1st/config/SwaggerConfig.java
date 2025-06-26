package com.example.mincheol1st.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Mincheol 게시판 API",
                version = "1.0",
                description = "게시판 프로젝트 API 문서입니다.")
)

public class SwaggerConfig {
}
