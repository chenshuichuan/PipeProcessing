package com.ruoyi.project.prepage.batch.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.prepage.batch.service.IPrePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 加工批次 信息操作处理
 *
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/prepage")
public class PrePageController extends BaseController {
    private String prefix = "/prepage";

    @Autowired
    private IPrePageService batchService;

    @GetMapping({"/test","/test.html"})
    public String test() {
        return prefix + "/test";
    }

    @GetMapping({"/test2","/test2.html"})
    public String test2() {
        return prefix + "/test";
    }

}
