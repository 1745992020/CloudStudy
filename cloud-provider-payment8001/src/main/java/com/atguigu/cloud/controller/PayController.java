package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    public ResultData<String> addPay(@RequestBody Pay pay){
        log.info(pay.toString());
        int i = payService.add(pay);
        return ResultData.success("成功插入，返回值："+i);
    }

    @DeleteMapping("/pay/delete/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        return ResultData.success(payService.delete(id));
    }
    @PutMapping("/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);

        int i =  payService.update(pay);
        return ResultData.success("成功修改记录，返回值："+i);
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id){
        if(id<0)throw new RuntimeException("id不能为负数");
        return ResultData.success(payService.getById(id));
    }

    @GetMapping("pay/getAll")
    @Operation(summary = "查全部流水",description = "查询支付流水方法")
    public ResultData<List<Pay>> getAll(){
        return ResultData.success(payService.getAll());
    }


    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    private String getInfoByConsul(@Value("${test.info}") String info) {
        return "信息: " + info + " " + "port: " + port;
    }
}
