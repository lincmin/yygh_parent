package com.lincm.yygh.hosp.controller;

import com.lincm.yygh.hosp.service.HospitalSetService;
import com.lincm.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    //注入service
    @Autowired
    private HospitalSetService hospitalSetService;

    //访问路径http://localhost:8201/admin/hosp/hospitalSet/findAll

    //查询医院设置表所有信息
    @GetMapping("findAll")
    public List<HospitalSet> findAllHospitalSet(){
        //调用service方法
        List<HospitalSet> list = hospitalSetService.list();
        //list转换json返回
        return list;
    }
}
