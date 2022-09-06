package com.epam.selectioncommittee.model;

import com.epam.selectioncommittee.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String message;
    private ErrorType errorType;
    private LocalDateTime timeStamp;
}
