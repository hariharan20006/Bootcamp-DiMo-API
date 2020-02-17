package com.bootcamp.dev.devcamp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse  {
    private String message;


    public static SuccessResponse justSuccess() {
        return new SuccessResponse("Success");
    }
}
