package com.nova.student.service;

import com.nova.student.dao.StudentOrderRepository;
import com.nova.student.domain.Person;
import com.nova.student.domain.StudentOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOrderService.class);

    @Autowired
    private StudentOrderRepository studentOrderRepository;

    @Transactional
    public void testSave(){
        StudentOrder so = new StudentOrder();
        so.setWife(buildPerson(true));
        so.setHusband(buildPerson(false));
        studentOrderRepository.save(so);
    }

    @Transactional
    public void testGet(){
        List<StudentOrder> soList = studentOrderRepository.findAll();
        LOGGER.info(soList.get(0).getWife().getGivenName());
    }

    public Person buildPerson(boolean wife){
        Person p = new Person();
        p.setDateOfBirth(LocalDate.now());
        if(wife){
            p.setSurName("Рюрик");
            p.setGivenName("Марфа");
            p.setPatronymic("Васильевна");
        }else{
            p.setSurName("Рюрик");
            p.setGivenName("Иван");
            p.setPatronymic("Васильевич");
        }
        return p;
    }
}
