package com.aplikasi.karyawan.controller.validation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class ApiResponse {

    private Object data;
    private String message;
//    private boolean error = true;
    private int code;

    public ApiResponse(Object data, String message){
        this.data = data;
        this.message = message;
    }

    public ApiResponse(Object data, String message, int code){
        this.data = data;
        this.message = message;
        this.code = code;
    }
}

