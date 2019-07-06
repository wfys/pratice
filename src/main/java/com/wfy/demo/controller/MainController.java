package com.wfy.demo.controller;
import com.wfy.demo.dao.Result;
import com.wfy.demo.dao.Test;
import com.wfy.demo.repository.ResultReposity;
import com.wfy.demo.service.TestService;

import com.wfy.demo.util.AsyncTask;
import com.wfy.demo.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@Api(tags = "测试Controller")
@AllArgsConstructor
public class MainController {

    private final TestService testService;
    private final ResultReposity resultRepository;
    private final AsyncTask asyncTask;

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    /**
     * 首页
     * @param model
     * @return
     */
    @ApiOperation(value = "测试首页", notes = "测试首页")
    @GetMapping("/index")
    public ModelAndView index(Model model) {
        model.addAttribute("headTitle","首页");
        return new ModelAndView("index","model", model);
    }

    /**
     * 测试处理
     * @param gridCount
     * @param times
     * @param model
     * @return
     */
    @ApiOperation(value = "进行测试处理操作", notes = "根据格子上数和循环次数进行测试处理操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gridCount", value = "格子数"),
            @ApiImplicitParam(name = "times", value = "循环次数")
    })
    @GetMapping("/test")
    public ResponseEntity<Response> test(@RequestParam(value="gridCount",defaultValue="0") int gridCount,
                                         @RequestParam(value="times",defaultValue="0" ) int times,
                                         Model model) {


        Test test=new Test(gridCount,times);
        Test newTest=testService.saveTest(test);
        asyncTask.mainAsync(newTest,testService,gridCount,times);
        //List<Result> resultList=resultRepository.findByTestOrderById(test);
        //model.addAttribute("resultList", resultList);
        //Model testId = model.addAttribute("testId", test.getId());
        ResponseEntity<Response> body = ResponseEntity.ok().body(new Response(true,test.getId()+""));
        return body;
        //return new ModelAndView("detailRecord","model", model);
    }

    /**
     * 测试结果总结
     * @param testId
     * @param model
     * @return
     */
    @ApiOperation(value = "进行测试结果总结", notes = "根据测试结果进行测试结果总结")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testId", value = "格子数", required = true)
    })
    @GetMapping("/testSummary")
    public ModelAndView testSummary(@RequestParam(value="testId",defaultValue="1") Long testId,
                             Model model) {
        Test test=testService.getTestById(testId);
        //Integer openSuccess=resultRepository.findAllSuccessCount(testId,true,"1");
        //Integer closeSuccess=resultRepository.findAllSuccessCount(testId,true,"2");
        List<Integer> girdSuccessList=new ArrayList<>();
        for(int i=0;i<test.getGridCount();i++){
            girdSuccessList.add(resultRepository.findSuccessCount(testId,true,i+""));
        }

        model.addAttribute("girdSuccessList", girdSuccessList);
        model.addAttribute("test", test);
        return new ModelAndView("detailSummary","model", model);
    }

    /**
     * 历史记录
     * @param model
     * @return
     */
    @ApiOperation(value = "进行历史记录总结", notes = "根据历史记录进行详细查看")
    @GetMapping("/historyRecord")
    public ModelAndView historyRecord(Model model) {

        List<Test> testList=testService.listTests();

        Collections.reverse(testList);
        model.addAttribute("headTitle","历史记录");
        model.addAttribute("testList", testList);
        return new ModelAndView("historyRecord","model", model);
    }

    /**
     * 某一次测试的结果
     * @param testId
     * @param model
     * @return
     */
    @ApiOperation(value = "进行测试详细结果展示", notes = "根据测试结果ID进行测试详细结果展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "testId", value = "格子数", required = true)
    })
    @GetMapping("/historyDetail")
    public ModelAndView historyDetail(@RequestParam(value="testId",defaultValue="1") Long testId,
                                      Model model) {

        Test test=testService.getTestById(testId);
        List<Result> resultList=resultRepository.findByTestOrderById(test);
        model.addAttribute("resultList", resultList);
        model.addAttribute("testId", test.getId());
        return new ModelAndView("detailRecord","model", model);
    }
}
