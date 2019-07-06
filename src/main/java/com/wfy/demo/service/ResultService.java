package com.wfy.demo.service;

import com.wfy.demo.dao.Result;
import com.wfy.demo.dao.Test;

import java.util.List;

public interface ResultService {

    Result saveResult(Result result);

    Result getResultById(Long id);

    List<Result> listTests(Test test);

    Integer findSuccessCount(Long id,Boolean success,String lockerNumber);

    Integer findAllSuccessCount(Long id,Boolean success,String type);
}
