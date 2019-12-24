package com.tuanzhang.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String userName;

    public boolean valdate(){
        return !StringUtils.isEmpty(userName);
    }
}
