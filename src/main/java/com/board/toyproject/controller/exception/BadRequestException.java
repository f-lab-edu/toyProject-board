package com.board.toyproject.controller.exception;

import java.util.List;
import org.springframework.validation.ObjectError;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }

}