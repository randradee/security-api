package com.randradee.studies.security.domain.user;

public record RegisterDTO(
        String login,
        String password,
        UserRole role
) {
}
