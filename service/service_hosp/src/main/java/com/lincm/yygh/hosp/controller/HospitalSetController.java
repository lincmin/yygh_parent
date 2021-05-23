package com.lincm.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lincm.yygh.common.result.Result;
import com.lincm.yygh.hosp.service.HospitalSetService;
import com.lincm.yygh.model.hosp.HospitalSet;
import com.lincm.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {
    //注入service
    @Autowired
    private HospitalSetService hospitalSetService;

    //访问路径http://localhost:8201/admin/hosp/hospitalSet/findAll

    //查询医院设置表所有信息
    @ApiOperation(value="获取所有医院设置信息")
    @GetMapping("findAll")
    public Result findAllHospitalSet(){
        //调用service方法
        List<HospitalSet> list = hospitalSetService.list();
        //list转换json返回
        return Result.ok(list);
    }

    //逻辑删除医院设置
    @ApiOperation(value="逻辑删除医院设置信息")
    @ApiParam(name="id",value="医院设置id",required=true)
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
       if(flag){
           return Result.ok();
       }else{
           return Result.fail();
       }
    }

    //条件查询带分页
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo){
        //创建page对象,传递当前页,每页记录数
        Page<HospitalSet> page = new Page<>(current,limit);
        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();
        String hoscode = hospitalSetQueryVo.getHoscode();
        if(!StringUtils.isEmpty(hosname)){
            wrapper.like("hosname",hospitalSetQueryVo.getHosname());
        }
        if(!StringUtils.isEmpty(hoscode)){
            wrapper.eq("hoscode",hospitalSetQueryVo.getHoscode());
        }

        //调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page,wrapper);
        return Result.ok(pageHospitalSet);
    }

    //添加医院设置

    //根据id获取医院设置

    //修改医院设置

    //批量删除医院设置
}
