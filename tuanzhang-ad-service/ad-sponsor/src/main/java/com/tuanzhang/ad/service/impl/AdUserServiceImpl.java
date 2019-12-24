package com.tuanzhang.ad.service.impl;
import com.tuanzhang.ad.constant.Constants;
import com.tuanzhang.ad.dao.AdUserRepository;
import com.tuanzhang.ad.entity.AdUser;
import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.service.AdUserService;
import com.tuanzhang.ad.utils.CommonUtils;
import com.tuanzhang.ad.vo.CreateUserRequest;
import com.tuanzhang.ad.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("adUserService")
@Slf4j
public class AdUserServiceImpl implements AdUserService {

    @Resource
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws Exception {
        if (!request.valdate()) {
            throw new AdException(Constants.ERRORMSG.REQUEST_PARAMS_ERR);
        }

        AdUser user = adUserRepository.findByUsername(request.getUserName());
        if (null != user) {
            throw new AdException(Constants.ERRORMSG.SAME_NAME_ERR);
        }

        adUserRepository.save(new AdUser(request.getUserName(), CommonUtils.md5(request.getUserName())));
        return new CreateUserResponse(user.getId(), user.getUsername(),
                user.getToken(), user.getCreateTime(), user.getUpdateTime());
    }
}
