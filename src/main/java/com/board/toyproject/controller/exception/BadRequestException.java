package com.board.toyproject.controller.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String errorMsg) {
        super(errorMsg);
    }
}