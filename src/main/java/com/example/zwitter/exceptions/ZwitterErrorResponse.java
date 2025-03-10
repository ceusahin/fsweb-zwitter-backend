package com.example.zwitter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZwitterErrorResponse {
    private String message;
    private int status;
    private long timestamp;
}
