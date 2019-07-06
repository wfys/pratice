package com.wfy.demo.util;

import com.alibaba.fastjson.JSONObject;

public class TestThread extends Thread{
    private Boolean isOpen=false;
    private String cabinet;
    private String lockerNumber;
    public TestThread(Boolean isOpen,String cabinet,String lockerNumber){
        this.isOpen=isOpen;
        this.cabinet=cabinet;
        this.lockerNumber=lockerNumber;
    }
    @Override
    public void run() {
        JSONObject json1=new JSONObject();
        json1.put("deviceId","0000000002");
        json1.put("cabinet", cabinet);
        json1.put("lockerNumber",lockerNumber);
        json1.put("type", "2");
        try {
            while(isOpen){
                String result=HttpHelp.httpPost("http://39.98.91.160:8091/mq/send",json1.toString());
                JSONObject jsonObject1 = JSONObject.parseObject(result);
                JSONObject msgObject1 = (JSONObject) jsonObject1.get("msg");
                isOpen=(Boolean)msgObject1.get("gridIsOpen");
                if(isOpen){
                    Thread.sleep(500);
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
}
