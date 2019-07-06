package com.wfy.demo.repository;

import com.wfy.demo.dao.Result;
import com.wfy.demo.dao.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResultReposity extends JpaRepository<Result, Long> {
    List<Result> findByTestOrderById(Test test);
    //统计总的次数
    @Query(value = "select count(*) from result where test_id= ?1", nativeQuery = true)
    Integer findCount(Long id);

    @Query(value = "select count(*) from result where test_id= ?1 and success = ?2 and locker_number = ?3", nativeQuery = true)
    Integer findSuccessCount(Long id,Boolean success,String lockerNumber);

    @Query(value = "select count(*) from result where test_id= ?1 and success = ?2 and type = ?3", nativeQuery = true)
    Integer findAllSuccessCount(Long id,Boolean success,String type);
}
