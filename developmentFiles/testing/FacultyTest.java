package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testClasses.Course;
import testClasses.Faculty;
import testClasses.Major;
import testClasses.Student;
import testClasses.User;
import testClasses.UserType;
import testClasses.Warnings;

public class FacultyTest {
    
    private Faculty faculty;

    @BeforeEach
    public void setup() {
		UUID userUUID = UUID.randomUUID();
        String firstName = "Osbert";
        String lastName = "Odden";
        String userEmail = "oodden@csc.sc.edu";
        String userPass = "tempP4SSisNOTTEMP";
        String userID = "oodden";
        UserType userType = UserType.ADVISOR;
        HashMap<UUID, User> advisingStudents = new HashMap<>();
        HashMap<UUID, Course> coursesInstructing = new HashMap<>();

        faculty = new Faculty(userUUID, firstName, lastName, userEmail, userPass, userID, userType, advisingStudents, coursesInstructing);
	}

    @AfterEach

    public void tearDown() {
		//Clean up after each test
        faculty = null;
	}

    @Test

    void testGetAdvisingStudents() {
        ArrayList<User> advisingStudents = faculty.getAdvisingStudents();
        assertNotNull(advisingStudents);
        assertEquals(0, advisingStudents.size());

    }

    @Test
    void testAddAdvisingStudent() {
    // Test adding an advising student
    UUID userUUID = UUID.randomUUID();
    String firstName = "Belon";
    String lastName = "Gatsby";
    String userEmail = "belon.gatsby11@email.sc.edu";
    String userPass = "iLoveMyCat!32";
    String userID = "bgatsby";
    UserType userType = UserType.STUDENT;
    HashMap<Course, String> completedCourses = new HashMap<>();
    ArrayList<Course> currentCourses = new ArrayList<>();
    ArrayList<Course> incompleteCourses = new ArrayList<>();
    ArrayList<Warnings> warnings = new ArrayList<>();
    int completedHours = 0;
    int currentHours = 0;
    ArrayList<UUID> advisementPlans = new ArrayList<>();
    double GPA = 3.5; // Example GPA
    boolean hasScholarships = true;
    Major currMajor = Major.COMPUTER_SCIENCE; // Example Major
        Student student = new Student(userUUID, firstName, lastName, userEmail, userPass, userID, userType,
            completedCourses, currentCourses, incompleteCourses, warnings, completedHours, currentHours,
            advisementPlans, GPA, hasScholarships, currMajor);
        assertTrue(faculty.addAdvisingStudent(student));
        assertEquals(1, faculty.getAdvisingStudents().size());
    }

    @Test
    void testGetCoursesInstructing() {
        // Test if the list of courses instructing is retrieved correctly
        ArrayList<Course> coursesInstructing = faculty.getCoursesInstructing();
        assertNotNull(coursesInstructing);
        assertEquals(0, coursesInstructing.size()); // Assuming initially there are no courses instructing
    }

    @Test
    void testGetAllStudents() {
        // Test if all students are retrieved correctly
        ArrayList<User> allStudents = faculty.getAllStudents();
        assertNotNull(allStudents);
        // Add assertions based on your expected behavior
    }



}
