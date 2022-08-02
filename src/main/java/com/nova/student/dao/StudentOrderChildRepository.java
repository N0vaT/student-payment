package com.nova.student.dao;

import com.nova.student.domain.StudentOrderChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentOrderChildRepository extends JpaRepository<StudentOrderChild, Long> {
}
