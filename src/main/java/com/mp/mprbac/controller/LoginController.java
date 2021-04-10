package com.mp.mprbac.controller;


import com.mp.mprbac.dto.LoginDto;
import com.mp.mprbac.service.AccountService;
import com.mp.mprbac.service.ResourceService;
import com.mp.mprbac.vo.ResourceVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 登录 前端控制器
 * </p>
 *
 * @author seven
 * @since 2021-04-03
 */
@Controller
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @param session 会话
     * @param redirectAttributes 重定向，可以携带重定向的数据
     * @return
     */
    @PostMapping("login")
    public String login(String username,
                        String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes,
                        Model model) {
        LoginDto loginDto = accountService.login(username, password);
        String error = loginDto.getError();
        if (StringUtils.isEmpty(error)) {
            // 将账号信息存入session
            session.setAttribute("account", loginDto.getAccount());

            // 查询资源信息并且存入model
            List<ResourceVO> resourceVOS = resourceService.listResourceByRoleId(loginDto.getAccount().getRoleId());
            model.addAttribute("resources", resourceVOS);


        } else {
            redirectAttributes.addFlashAttribute("error", error);
        }
        return loginDto.getPath();
    }

    /**
     * 退出
     * @param session 会话
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 销毁session
        return "redirect:/"; // 默认重定向到登录页
    }

}
