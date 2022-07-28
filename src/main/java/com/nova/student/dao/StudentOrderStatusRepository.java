package com.nova.student.dao;

import com.nova.student.domain.StudentOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrderStatusRepository extends JpaRepository<StudentOrderStatus, Long> {
}
