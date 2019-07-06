package com.wfy.demo.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity // 单个结果实体
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; //记录标识的唯一ID

    @Column(name="deviceId")
    private String deviceId = "";  // 设备ID

    @Column(name="type")
    private String type = "";  // 类型

    @Column(name="cabinet")
    private String cabinet = "";  // 储物柜

    @Column(name="lockerNumber")
    private String lockerNumber = "";  // 格子编号

    @Column(name="open",nullable = false)
    private Boolean open = false;  // 是否打开

    @Column(name="success",nullable = false)
    private Boolean success= false;  // 是否执行成功与否

    @Column(name="description")
    private String description = "";  // 描述

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="test_id")
    private Test test;

    protected Result() {
        // TODO Auto-generated constructor stub
    }

    public Result(String deviceId, String type,String cabinet) {
        this.deviceId = deviceId;
        this.type = type;
        this.cabinet = cabinet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    public void setLockerNumber(String lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
