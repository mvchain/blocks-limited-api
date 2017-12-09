package com.mvc.invite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.invite.mapper.InviteUserMapper;
import com.mvc.invite.model.Auth;
import com.mvc.invite.model.InviteUser;
import com.mvc.invite.model.InviteUserVO;
import com.mvc.invite.service.InviteService;
import com.mvc.invite.service.ResponseGenerator;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ywd-pc on 2017/12/8.
 */
@RestController
@Log
public class InviteController {
    @Autowired
    private InviteUserMapper inviteUserMapper;
    @Autowired
    private InviteService inviteService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResponseGenerator responseGenerator;

    @GetMapping("/inviteUser")
    public String inviteUser(@RequestParam String password)throws JsonProcessingException {
        Auth auth = new Auth(Auth.EMP, password);
        if (inviteService.auth(auth)) {
            List<InviteUser> inviteUsers = inviteUserMapper.selectAll();
            return responseGenerator.success(InviteUserVO.convert(inviteUsers));
        } else {
            return responseGenerator.fail("密码错误！");
        }
    }

    @GetMapping("/inviteUser/{cellphone}")
    public String inviteUserByPhone(@PathVariable String cellphone)throws JsonProcessingException {
        InviteUser inviteUser = inviteUserMapper.selectByPhone(cellphone);
        return responseGenerator.success(inviteUser);
    }

    @PostMapping("/inviteUser")
    public String inviteUser(
            @RequestParam String name,
            @RequestParam String cellphone,
            @RequestParam String address) throws JsonProcessingException {
        // Check if cellphone already exists.
        InviteUser preUser = inviteUserMapper.selectByPhone(cellphone);
        if (preUser != null) {
            return responseGenerator.success(0);
        }

        InviteUser inviteUser = new InviteUser(name, cellphone, address);
        inviteUser.setInviteCode(inviteService.generateUnduplicatedInviteCode());
        int result = inviteUserMapper.insert(inviteUser);
        return responseGenerator.success(result);
    }

    @PostMapping("/inviteCount")
    public String inviteCount(
            @RequestParam String password,
            @RequestParam String csvData) throws JsonProcessingException {

        Auth auth = new Auth(Auth.TECH, password);
        if (inviteService.auth(auth)) {
            int updateCount = inviteService.batchUpdate(csvData);
            return responseGenerator.success(updateCount);
        } else {
            return responseGenerator.fail("密码错误！");
        }
    }
}
