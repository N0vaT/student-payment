package com.nova.student.service;

import com.nova.student.dao.*;
import com.nova.student.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOrderService.class);

    @Autowired
    private StudentOrderRepository studentOrderRepository;
    @Autowired
    private StudentOrderChildRepository studentOrderChildRepository;
    @Autowired
    private PassportOfficeRepository passportOfficeRepository;
    @Autowired
    private RegisterOfficeRepository registerOfficeRepository;
    @Autowired
    private StreetRepository streetRepository;
    @Autowired
    private StudentOrderStatusRepository statusRepository;
    @Autowired
    private UniversityRepository universityRepository;


    @Transactional
    public void testSave(){
        StudentOrder so = new StudentOrder();
        so.setStatus(statusRepository.getOne(1L));
        so.setStudentOrderDate(LocalDateTime.now());
        so.setWife(buildPerson(true));
        so.setHusband(buildPerson(false));
        so.setCertificateNumber("001");
        so.setRegisterOffice(registerOfficeRepository.getOne(1L));
        so.setMarriageDate(LocalDate.now());

        studentOrderRepository.save(so);

        studentOrderChildRepository.save(buildChild(so));
    }

    @Transactional
    public void testGet(){
        List<StudentOrder> soList = studentOrderRepository.findAll();
        soList.forEach(s-> {
            if (s.getChildren().size() > 0) {
                LOGGER.info("Дети есть, ребенка зовут - {}",s.getChildren().get(0).getChild().getGivenName());
            } else {
                LOGGER.info("Детей нет, жену зовут - {}",s.getWife().getGivenName());
            }
        });
//        LOGGER.info(soList.get(0).getWife().getGivenName());
//        LOGGER.info(soList.get(0).getChildren().get(0).getGivenName());
    }

    public Adult buildPerson(boolean wife){
        Adult p = new Adult();
        p.setDateOfBirth(LocalDate.now());
        Address a = new Address();
        a.setPostCode("190000");
        a.setStreet(streetRepository.getOne(1L));
        a.setBuilding("21");
        a.setExtension("B");
        a.setApartment("199");
        p.setAddress(a);
        if(wife){
            p.setSurName("Рюрик");
            p.setGivenName("Марфа");
            p.setPatronymic("Васильевна");
            p.setPassportOffice(passportOfficeRepository.getOne(1L));
            p.setPassportSeria("WIFE_S");
            p.setPassportNumber("WIFE_N");
            p.setIssueDate(LocalDate.now());
            p.setUniversity(universityRepository.getOne(1L));
            p.setStudentNumber("999999");
        }else{
            p.setSurName("Рюрик");
            p.setGivenName("Иван");
            p.setPatronymic("Васильевич");
            p.setPassportOffice(passportOfficeRepository.getOne(2L));
            p.setPassportSeria("HUSBAND_S");
            p.setPassportNumber("HUSBAND_N");
            p.setIssueDate(LocalDate.now());
            p.setUniversity(universityRepository.getOne(2L));
            p.setStudentNumber("111111");
        }
        return p;
    }

    public StudentOrderChild buildChild(StudentOrder so){
        StudentOrderChild co = new StudentOrderChild();
        co.setStudentOrder(so);
        Child c = new Child();
        c.setSurName("Рюрик");
        c.setGivenName("Dmitriy");
        c.setPatronymic("Ivanovich");
        c.setDateOfBirth(LocalDate.now());
        Address a = new Address();
        a.setPostCode("190000");
        a.setStreet(streetRepository.getOne(1L));
        a.setBuilding("21");
        a.setExtension("B");
        a.setApartment("199");
        c.setAddress(a);
        c.setCertificateNumber("CHILD_N");
        c.setCertificateDate(LocalDate.now());
        c.setRegisterOffice(registerOfficeRepository.getOne(1L));

        co.setChild(c);
        return co;
    }

}
