package com.example.zwitter.dto;

import java.time.LocalDateTime;

public record ZweetRecord (Long id, String username, String content, LocalDateTime createdAt) {
}
