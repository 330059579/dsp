package com.tuanzhang.ad.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResult<T> implements Serializable {

    private static final int SUCCESS   = 0;
    private static final int FAIL      = 1;
    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_MESSAGE = "fail";

    private int code;
    private String message;
    private T data;

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void success(){
        this.code = SUCCESS;
        this.message = SUCCESS_MESSAGE;
    }

    public void success(T data){
        this.code = SUCCESS;
        this.message = SUCCESS_MESSAGE;
        this.data = data;
    }

    public boolean isSuccess(){
        if (this.code == SUCCESS) {
            return true;
        }

        return false;
    }

    public void fail(){
        this.code = FAIL;
        this.message = FAIL_MESSAGE;
    }


}
