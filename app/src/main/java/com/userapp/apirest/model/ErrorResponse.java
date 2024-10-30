package com.userapp.apirest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private Integer code;
    private String messageString;
}
