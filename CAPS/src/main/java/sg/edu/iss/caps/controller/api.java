package sg.edu.iss.caps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.repo.LecturerRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class api {

    @Autowired
    LecturerRepository lrepo;


    @GetMapping("/coursedata")
    public ArrayList<Lecturer> getLecturerData() {
        return (ArrayList<Lecturer>) lrepo.findAll();
    }
}
