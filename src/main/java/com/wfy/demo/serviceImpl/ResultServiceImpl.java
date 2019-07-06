package com.wfy.demo.serviceImpl;

import com.wfy.demo.dao.Result;
import com.wfy.demo.dao.Test;
import com.wfy.demo.repository.ResultReposity;
import com.wfy.demo.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    private ResultReposity resultRepository;

    @Transactional
    @Override
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public List<Result> listTests(Test test) {
        return resultRepository.findByTestOrderById(test);
    }

    @Override
    public Result getResultById(Long id) {
        return resultRepository.getOne(id);
    }

    @Override
    public Integer findSuccessCount(Long id, Boolean success, String lockerNumber) {
        return resultRepository.findSuccessCount(id, success, lockerNumber);
    }

    @Override
    public Integer findAllSuccessCount(Long id, Boolean success, String type) {
        return resultRepository.findAllSuccessCount(id, success, type);
    }
}
