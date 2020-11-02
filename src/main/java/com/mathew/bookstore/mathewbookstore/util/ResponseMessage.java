package com.mathew.bookstore.mathewbookstore.util;

import lombok.Data;

@Data
public class ResponseMessage {
    int code;
    private String message;
}
