package com.mvc.invite.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.invite.mapper.InviteUserMapper;
import com.mvc.invite.mapper.KsOrderMapper;
import com.mvc.invite.model.Auth;
import com.mvc.invite.model.InviteUser;
import com.mvc.invite.model.InviteUserVO;
import com.mvc.invite.model.KsOrder;
import com.mvc.invite.service.InviteService;
import com.mvc.invite.service.ResponseGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ywd-pc on 2017/12/8.
 */
@RestController
@Log
@Api(value = "库神钱包", description = "")
public class InviteController {

    @Value("${price}")
    private Integer price;

    @Autowired
    private InviteUserMapper inviteUserMapper;
    @Autowired
    private KsOrderMapper ksOrderMapper;
    @Autowired
    private InviteService inviteService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResponseGenerator responseGenerator;

    @ApiOperation(value = "邀请码用户信息列表", notes = "邀请人数从大到小")
    @GetMapping("/inviteUser")
    public String inviteUser()throws JsonProcessingException {
        List<InviteUser> inviteUsers = inviteUserMapper.selectAll();
        return responseGenerator.success(InviteUserVO.convert(inviteUsers));
    }

    @ApiOperation(value = "使用手机号查找邀请码用户")
    @GetMapping("/inviteUser/{cellphone}")
    public String inviteUserByPhone(@PathVariable String cellphone)throws JsonProcessingException {
        InviteUser inviteUser = inviteUserMapper.selectByPhone(cellphone);
        return responseGenerator.success(inviteUser);
    }


    @ApiOperation(value = "用户获得邀请码")
    @PostMapping("/inviteUser")
    public String inviteUser(
            @RequestParam String name,
            @RequestParam String cellphone,
            @RequestParam String address,
            @RequestParam String etherAddress) throws JsonProcessingException {
        // Check if cellphone already exists.
        InviteUser preUser = inviteUserMapper.selectByPhone(cellphone);
        if (preUser != null) {
            return responseGenerator.success(0);
        }

        InviteUser inviteUser = new InviteUser(name, cellphone, address, etherAddress);
        inviteUser.setInviteCode(inviteService.generateUnduplicatedInviteCode());
        int result = inviteUserMapper.insert(inviteUser);
        return responseGenerator.success(result);
    }


    @ApiOperation(value = "（Deprecated）上传订单数据")
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

    @ApiOperation(value = "下单", notes = "inviteCode非必填<br/>" +
            "返回插入ID")
    @PostMapping("/order")
    public String makeOrder(
        @RequestBody KsOrder ksOrder)throws Exception {
        ksOrder.setStatus(KsOrder.STATUS_UNPAID);
        ksOrder.setSum(ksOrder.getQuantity() * price);
        ksOrderMapper.insertKsOrder(ksOrder);
        return responseGenerator.success(ksOrder.getId());
    }

    @ApiOperation(value = "手机号查订单", notes = "")
    @GetMapping("/order/{cellphone}")
    public String orderByPhone(@PathVariable String cellphone) throws JsonProcessingException {
        List<KsOrder> list = ksOrderMapper.selectKsOrdersByPhone(cellphone);
        return responseGenerator.success(list);
    }

//    @ApiOperation(value = "审核列表", notes = "searchText为手机号和姓名搜索")
//    @GetMapping("/order")
//    public String orderlist(
//            @RequestParam String searchText) throws JsonProcessingException {
//        Condition condition = new Condition(KsOrder.class);
//        Example.Criteria criteria = condition.createCriteria();
//        criteria.andLike("cellphone", searchText);
//        criteria.andLike("name", searchText);
//        condition.setOrderByClause("updated_at desc");
//        List<KsOrder> list = ksOrderMapper.selectByExample(condition);
//        return responseGenerator.success(list);
//    }

    @ApiOperation(value = "审核列表", notes = "searchText为手机号和姓名搜索")
    @GetMapping("/order")
    public String orderlist(
            @RequestParam String searchText) throws JsonProcessingException {
        List<KsOrder> list = new ArrayList<>();
        if (StringUtils.isEmpty(searchText)) {
            list = ksOrderMapper.selectKsOrders();
        } else {
            list = ksOrderMapper.selectKsOrdersBySearchText(searchText);
        }
        KsOrder.fillDateStr(list);
        return responseGenerator.success(list);
    }

    @ApiOperation(value = "确认发货", notes = "data为1表示成功")
    @PutMapping("/order/delivery")
    public String orderDelivered(@RequestParam Integer id) throws JsonProcessingException {
        int result = ksOrderMapper.updateStatus(id, KsOrder.STATUS_DELIVERING);
        return responseGenerator.success(result);
    }

    @ApiOperation(value = "完成订单", notes = "data为1表示成功")
    @PutMapping("/order/finish")
    public String orderFinished(@RequestParam Integer id) throws JsonProcessingException {
        int result = ksOrderMapper.updateStatus(id, KsOrder.STATUS_FINISHED);
        return responseGenerator.success(result);
    }

    @ApiOperation(value = "审核通过", notes = "data为1表示成功")
    @PutMapping("/order/confirmation")
    public String orderConfirmed(@RequestParam Integer id) throws JsonProcessingException {
        int result = ksOrderMapper.updateStatus(id, KsOrder.STATUS_CONFIRMED);
        KsOrder ksOrder = ksOrderMapper.selectById(id);
        if (!StringUtils.isEmpty(ksOrder.getInviteCode())) {
            inviteUserMapper.updateCount(ksOrder.getInviteCode(), ksOrder.getQuantity());
        }
        return responseGenerator.success(result);
    }

    @ApiOperation(value = "确认付款", notes = "" +
            "data为1表示成功<br/>" +
            "payChannel 1 为支付宝，2 为银行转账<br/>")
    @PutMapping("/order/paid")
    public String orderPaid(
            @RequestParam Integer id,
            @RequestParam String payChannel,
            @RequestParam String payAccount,
            @RequestParam String payerName
    ) throws JsonProcessingException {
        int result = ksOrderMapper.updatePaid(id, KsOrder.STATUS_PAID, payChannel, payAccount, payerName);
        return responseGenerator.success(result);
    }

    @ApiOperation(value = "审核不通过", notes = "data为1表示成功")
    @PutMapping("/order/confirm/failure")
    public String orderConfirmFailure(@RequestParam Integer id, @RequestParam String comment) throws JsonProcessingException {
        int result = ksOrderMapper.updateComment(id, KsOrder.STATUS_UNCONFIRMED, comment);
        return responseGenerator.success(result);
    }


}
