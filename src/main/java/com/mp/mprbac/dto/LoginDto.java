package com.mp.mprbac.dto;

import com.mp.mprbac.entity.Account;
import lombok.Data;

@Data
public class LoginDto {

    /**
     * 重定向或者跳转的路径
     */
    private String path;

    /**
     * 错误提示信息
     */
    private String error;

    /**
     * 当前登录人的信息
     */
    private Account account;

}
