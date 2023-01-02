package com.dazuizui.business.controller;

import com.dazuizui.business.service.student.UniverstySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 大学板块控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/universty")
public class UniverstyController {
    @Autowired
    private UniverstySerivce universtySerivce;

    /**
     * @author 杨易达(Bryan yang) Yida yang
     * 查询所有大学
     *     该数据量极少不考虑优化暂时，后续再说，加入todo
     * @return List<Universty>
     */
    @GetMapping("/getalluniversty")
    public String queryAllUniversty(){
        return universtySerivce.queryAllUniversty();
    }
}
