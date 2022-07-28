package com.nova.student.service;

import com.nova.student.dao.StreetRepository;
import com.nova.student.dao.StudentOrderRepository;
import com.nova.student.domain.Address;
import com.nova.student.domain.Person;
import com.nova.student.domain.Street;
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

    @Autowired
    private StreetRepository streetRepository;

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
        Address a = new Address();
        a.setPostCode("190000");
        Street s = streetRepository.getOne(1L);
        a.setStreet(s);
        a.setBuilding("21");
        a.setExtension("B");
        a.setApartment("199");
        p.setAddress(a);
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
