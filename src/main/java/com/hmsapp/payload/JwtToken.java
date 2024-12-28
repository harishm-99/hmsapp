package com.hmsapp.payload;

import lombok.Data;

@Data
public class JwtToken {

    private String token;
    private String type;
}
