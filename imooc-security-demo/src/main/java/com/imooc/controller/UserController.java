/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserController
 * Author:   Administrator
 * Date:     2019/11/20 12:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.execption.UserNotExistExecption;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUsers() {

        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id}")
    @JsonView(User.UserDetails.class)
    public User getUser(@PathVariable("id") String id) throws UserNotExistExecption {
        throw new UserNotExistExecption("用户信息不存在",id);
     /*   User user = new User();
        user.setUsername("weijinhao");
        user.setPassword("122333434");
        return user;*/
    }
    @PostMapping
    public User creteaUser(@RequestBody User user) {
        user.setId("1");
        return user;
    }

    @PutMapping("/{id}")
    public User update(@Valid  @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setId("1");
        return user;
    }
}
