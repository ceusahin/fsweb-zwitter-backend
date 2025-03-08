package com.example.zwitter.dto;

import java.time.LocalDateTime;

public record RezweetRecord(Long id, String username, String content, LocalDateTime rezweetedAt) {
}
