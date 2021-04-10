package com.mp.mprbac.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.mp.mprbac.dto.LoginDto;
import com.mp.mprbac.entity.Account;
import com.mp.mprbac.mapper.AccountMapper;
import com.mp.mprbac.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author seven
 * @since 2021-04-03
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Override
    public LoginDto login(String username, String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setPath("redirect:/"); // 默认重定向到根路径页面（默认登录不成功）
        Account account = lambdaQuery().eq(Account::getUsername, username).one();
        if (null == account) {
            loginDto.setError("用户名不存在");
            return loginDto;
        }

        MD5 md5 = new MD5(account.getSalt().getBytes());
        String digestHex = md5.digestHex(password);
        if (!digestHex.equalsIgnoreCase(account.getPassword())) {
            loginDto.setError("密码错误");
            return loginDto;
        }

        loginDto.setPath("login/main"); // 校验通过登录后台管理页面
        loginDto.setAccount(account); // service层通常不允许session侵入，所以将登录人信息通过dto返回给controller
        return loginDto;
    }
}
