package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("feign/pay")
public class OrderController {
    @Resource
    private PayFeignApi payFeignApi;
    @PostMapping("/add")
    public ResultData addPay(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        try {
            System.out.println("开始："+ DateUtil.now());
            payFeignApi.getPayInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("结束："+ DateUtil.now());
        }
        return null;
    }

    @GetMapping("/mylb")
    public String mylb(){
        return payFeignApi.mylb();
    }
}
