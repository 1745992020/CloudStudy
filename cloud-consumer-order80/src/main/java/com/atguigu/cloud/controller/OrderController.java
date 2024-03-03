package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consumer/pay")
public class OrderController {
    //public static final String PaymentSrv_URL = "http://127.0.0.1:8001";//先写死
    public static final String PaymentSrv_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO,ResultData.class);
    }
    @GetMapping("/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/"+id, ResultData.class,id);
    }
    @GetMapping("/delete/{id}")
    public ResultData DeleteById(@PathVariable("id") Integer id){
        restTemplate.delete(PaymentSrv_URL+"/pay/delete/"+id,ResultData.class,id);
        return ResultData.success("success");

    }
    @GetMapping("/update")
    public ResultData updatePay(@RequestBody PayDTO payDTO){
        restTemplate.put(PaymentSrv_URL+"/pay/update",payDTO,ResultData.class);
        return ResultData.success("success");
    }
}
