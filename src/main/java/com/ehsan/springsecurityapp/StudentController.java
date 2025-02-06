package com.ehsan.springsecurityapp;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.access.AuthorizationManagerWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> student = new ArrayList<>(List.of(
            new Student(1, "Ehsan", 100),
            new Student(2, "Ali", 90),
            new Student(3, "Reza", 80)
    ));

     @GetMapping("/students")
     public List<Student> getStudents() {
         return student;
     }

    // @GetMapping("/students/{studentId}")
    // public Student getStudent(@PathVariable("studentId") Integer studentId) {
    //     return new Student(studentId, "Ehsan", "100");
    // }

     @PostMapping("/save-students")
     public Student saveStudent(@RequestBody Student student) {
         this.student.add(student);
         return student;
     }

     @GetMapping("/csrf-token")
     public CsrfToken getCsrf(HttpServletRequest request) {
         // for getting csrf token we use HttpServletRequest and with get attribute
         return (CsrfToken) request.getAttribute("_csrf");
     }

    // @PutMapping("/students/{studentId}")
    // public Student updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
    //     System.out.println(student);
    //     return student;
    // }

    // @DeleteMapping("/students/{studentId}")
    // public void deleteStudent(@PathVariable("studentId") Integer studentId) {
    //     System.out.println("Student with id " + studentId + " deleted");
    // }


}
