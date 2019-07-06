package com.wfy.demo.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity // ʵ实体
public class Test implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; //记录标识的唯一ID

    @Column(name="gridCount")
    private Integer gridCount = 0;  // 格子数

    @Column(name="times")
    private Integer times = 0;  // 重复次数

    @Column(name="success")
    private Integer success = 0;  // 成功次数

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp endTime;

    protected Test() {
        // TODO Auto-generated constructor stub
    }

    public Test(Integer gridCount, Integer times) {
        this.gridCount = gridCount;
        this.times = times;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGridCount() {
        return gridCount;
    }

    public void setGridCount(Integer gridCount) {
        this.gridCount = gridCount;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}
