//package com.example.controllerdemo.controllers;
//
//import com.example.controllerdemo.service.BusinessLogic;
//import com.example.controllerdemo.service.BusinessLogicImpl1;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//@Controller
//public class BusinessController {
//
//    @Autowired
//    BusinessLogic blogic;
//
//    public String someUseCaseCall() {
//        // pass in 1 into blogic countAllBooks. selection parameter and pick out 1 of the impl
//        // use methods in service impl layer without having to use new operator/new constructor.
//        // use methods and get job done. layering!
//        // can use for any module interfaces. hide impl in interfaces.
//        // don't need to NEW obj. no new instances
//
//        // in JPA controller: trying to inject repo. repo is higher level interface class. can use methods from JPARepository. JPA factory does selection of methods.
//        // repo is ok for CRUD.
//        // but if want more methods. eg check if employee has leave entitlement, check booking availability etc
//        // good to use service.
//        Double d = blogic.countAllBooks(1);
//        return "";
//    }
//
//}
