package com.wfy.demo.serviceImpl;

import com.wfy.demo.dao.Result;
import com.wfy.demo.dao.Test;
import com.wfy.demo.repository.ResultReposity;
import com.wfy.demo.repository.TestRepository;
import com.wfy.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private ResultReposity resultRepository;

    @Transactional
    @Override
    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    @Override
    public Test getTestById(Long id) {
        return testRepository.getOne(id);
    }

    @Override
    public List<Test> listTests() {
        return testRepository.findAll();
    }

    @Override
    public Result createResult(Long testId, String deviceId, String type,String cabinet,
                             String lockerNumber,Boolean isOpen,Boolean isSuccess,String description) {
        Test test=testRepository.getOne(testId);
        Result result=new Result(deviceId,type,cabinet);
        result.setLockerNumber(lockerNumber);
        result.setDescription(description);
        result.setTest(test);
        result.setSuccess(isSuccess);
        result.setOpen(isOpen);
        return resultRepository.save(result);
        //System.out.println("ID:"+newResult.getId());

    }
}
