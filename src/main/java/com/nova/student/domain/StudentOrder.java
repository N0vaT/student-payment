package com.nova.student.domain;


import javax.persistence.*;

@Entity
@Table(name = "jc_student_order")
public class StudentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_order_id")
    private Long studentOrderId;

    public Long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(Long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }
}
