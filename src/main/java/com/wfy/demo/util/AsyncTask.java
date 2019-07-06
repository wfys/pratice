package com.wfy.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.wfy.demo.dao.Test;
import com.wfy.demo.service.TestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 异步执行开门、关门操作
 */
@Component
public class AsyncTask {

    @Async
    public void mainAsync(Test test, TestService testService, int gridCount, int times){
        int success = 0;
        Boolean isOpen=true;
        for(int i=0;i<times;i++){
            for(int j=0;j<gridCount;j++){
                String iCabinet = "2";
                String iGrid = "" + j;
                int k=0;
                for(k=0;k<20;k++){
                    if(isOpen){
                        String resc = closeCabinet(iCabinet,iGrid);
                        JSONObject jsonObject1 = JSONObject.parseObject(resc);
                        JSONObject msgObject1 = (JSONObject) jsonObject1.get("msg");
                        isOpen=(Boolean)msgObject1.get("gridIsOpen");
                        System.out.println("请关闭第"+iGrid+"号格子,第"+k+"提示");
                    }else{
                        break;
                    }
                }
                if(k==20){
                    String resc = closeCabinet(iCabinet,iGrid);
                    JSONObject jsonObject1 = JSONObject.parseObject(resc);
                    JSONObject msgObject1 = (JSONObject) jsonObject1.get("msg");
                    isOpen=(Boolean)msgObject1.get("gridIsOpen");
                    testService.createResult(test.getId(),msgObject1.get("deviceId")+"",msgObject1.get("type")+"",
                            msgObject1.get("cabinet")+"",msgObject1.get("lockerNumber")+"",(Boolean)msgObject1.get("gridIsOpen"),
                            false,"没有及时关闭格子");
                    continue;
                }
                String resp = openCabinet(iCabinet,iGrid);
                JSONObject jsonObject = JSONObject.parseObject(resp);
                JSONObject msgObject = (JSONObject) jsonObject.get("msg");
                //System.out.println(jsonObject);
                isOpen=(Boolean)msgObject.get("gridIsOpen");
                if(isOpen){
                    ++success;
                    testService.createResult(test.getId(),msgObject.get("deviceId")+"",msgObject.get("type")+"",
                            msgObject.get("cabinet")+"",msgObject.get("lockerNumber")+"",(Boolean)msgObject.get("gridIsOpen"),
                            true,msgObject.get("description")+"");
                }
                else{
                    testService.createResult(test.getId(),msgObject.get("deviceId")+"",msgObject.get("type")+"",
                            msgObject.get("cabinet")+"",msgObject.get("lockerNumber")+"",(Boolean)msgObject.get("gridIsOpen"),
                            false,msgObject.get("description")+"");
                }
            }
        }
        test.setSuccess(success);
        test.setEndTime(new Timestamp(System.currentTimeMillis()));
        testService.saveTest(test);
    }
    public String openCabinet(String cabinet,String lockerNumber){
        JSONObject json1=new JSONObject();
        json1.put("deviceId","0000000002");
        json1.put("cabinet", cabinet);
        json1.put("lockerNumber",lockerNumber);
        json1.put("type", "1");
        String result= HttpHelp.httpPost("http://39.98.91.160:8091/mq/send",json1.toString());
        return result;
    }
    public String closeCabinet(String cabinet,String lockerNumber){
        JSONObject json1=new JSONObject();
        json1.put("deviceId","0000000002");
        json1.put("cabinet", cabinet);
        json1.put("lockerNumber",lockerNumber);
        json1.put("type", "2");
        String result=HttpHelp.httpPost("http://39.98.91.160:8091/mq/send",json1.toString());
        return result;
    }
}
