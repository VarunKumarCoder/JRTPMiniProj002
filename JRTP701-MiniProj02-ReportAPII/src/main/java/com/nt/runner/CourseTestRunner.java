package com.nt.runner;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.entity.CourseDetails;
import com.nt.repository.ICourseDetailsRepository;

@Component
public class CourseTestRunner implements CommandLineRunner {
   @Autowired
    private ICourseDetailsRepository repository; 

    @Override
    public void run(String... args) throws Exception {
        // Create sample course details
        CourseDetails course1 = new CourseDetails("Admin1");
        course1.setCourseName("Java Full Stack");
        course1.setLocation("Hyderabad");
        course1.setCourseCategory("Software Development");
        course1.setFacultyName("Ravi Kumar");
        course1.setFee(35000.0);
        course1.setAdminContact(9876543210L);
        course1.setTrainingMode("Online");
        course1.setStartDate(LocalDateTime.of(2025, 3, 10, 9, 0));
        course1.setCourseStatus("Active");
        course1.setCreatedBy("System");
        course1.setUpdatedBy("System");

        CourseDetails course2 = new CourseDetails("Admin2");
        course2.setCourseName("Spring Boot & Microservices");
        course2.setLocation("Bangalore");
        course2.setCourseCategory("Backend Development");
        course2.setFacultyName("Anita Sharma");
        course2.setFee(40000.0);
        course2.setAdminContact(9876543211L);
        course2.setTrainingMode("Offline");
        course2.setStartDate(LocalDateTime.of(2025, 4, 5, 10, 30));
        course2.setCourseStatus("Active");
        course2.setCreatedBy("Admin2");
        course2.setUpdatedBy("Admin2");

        CourseDetails course3 = new CourseDetails("Admin3");
        course3.setCourseName("Data Science with Python");
        course3.setLocation("Pune");
        course3.setCourseCategory("Data Science");
        course3.setFacultyName("Rahul Verma");
        course3.setFee(50000.0);
        course3.setAdminContact(9876543212L);
        course3.setTrainingMode("Hybrid");
        course3.setStartDate(LocalDateTime.of(2025, 5, 20, 11, 0));
        course3.setCourseStatus("Upcoming");
        course3.setCreatedBy("Admin3");
        course3.setUpdatedBy("Admin3");
        
       

        // Save all courses
        repository.saveAll(Arrays.asList(course1, course2, course3));

        System.out.println("Sample course data inserted successfully!");
    }
}
