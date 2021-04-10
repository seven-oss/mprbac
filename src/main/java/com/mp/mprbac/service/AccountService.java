package com.mp.mprbac.service;

import com.mp.mprbac.dto.LoginDto;
import com.mp.mprbac.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author seven
 * @since 2021-04-03
 */
public interface AccountService extends IService<Account> {

    LoginDto login(String username, String password);

}
