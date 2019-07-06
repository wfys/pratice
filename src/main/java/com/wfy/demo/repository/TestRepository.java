package com.wfy.demo.repository;

import com.wfy.demo.dao.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
