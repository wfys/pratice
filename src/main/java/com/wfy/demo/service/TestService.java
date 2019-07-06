package com.wfy.demo.service;

import com.wfy.demo.dao.Result;
import com.wfy.demo.dao.Test;

import java.util.List;

public interface TestService {

    Test saveTest(Test test);

    Test getTestById(Long id);

    List<Test> listTests();

    Result createResult(Long testId, String deviceId, String type, String cabinet,
                        String lockerNumber, Boolean isOpen, Boolean isSuccess, String description);
}
