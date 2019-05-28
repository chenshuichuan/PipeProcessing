package com.ruoyi.project.system.user.service;

import com.ruoyi.project.system.user.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/5/5
 * Time: 16:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Test
    public void resetUserPwd() {
        Long id = 1L;
        User user = userService.selectUserById(id);
        user.setPassword("admin123");
        userService.resetUserPwd(user);
    }
}