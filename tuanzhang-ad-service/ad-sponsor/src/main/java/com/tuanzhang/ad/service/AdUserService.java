package com.tuanzhang.ad.service;

import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.vo.CreateUserRequest;
import com.tuanzhang.ad.vo.CreateUserResponse;

public interface AdUserService {

    CreateUserResponse createUser(CreateUserRequest request) throws AdException;
}
