package com.project.boardadmin.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDto(
        Long id,
        AdminAccountDto userAccount,
        String title,
        String content,
        Set<String> hashtags,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleDto of(Long id, AdminAccountDto adminAccount, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleDto(id, adminAccount, title, content, hashtags, createdAt, createdBy, modifiedAt, modifiedBy);
    }

}
