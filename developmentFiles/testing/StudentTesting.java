package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import testClasses.Course;
import testClasses.CourseList;
import testClasses.Elective;
import testClasses.Major;
import testClasses.Student;
import testClasses.UserList;
import testClasses.UserType;
import testClasses.Warnings;
import testClasses.WriteFile;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTesting 
{
    private Student student;
    private CourseList courseList = CourseList.getInstance();
    //assertEquals(val1,val2)
	//assertFalse(val)
	//assertTrue(val)
	//assertSame(val1,val2)
	//assertNotSame(val1,val2)
	//assertNull(val)
	//assertNotNull(val)
    
    @BeforeEach //runs before each test
    public void setup()
    {
        // Example attributes for a Student object
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

        student = new Student(userUUID, firstName, lastName, userEmail, userPass, userID, userType,
            completedCourses, currentCourses, incompleteCourses, warnings, completedHours, currentHours,
            advisementPlans, GPA, hasScholarships, currMajor);

        WriteFile.writeStudent(student);
    }

    @AfterEach //runs after each test
    public void tearDown()
    {
        student = null;
    }

    @Test
    public void testSearchCourseValidCourse()
    {
        Course oracle = courseList.getCourse("CSCE101");
        Course check = student.searchCourse("CSCE101");
        assertEquals(oracle, check);
    }

    @Test
    public void testSearchCourseInvalidCourse()
    {
        Course oracle = courseList.getCourse("CSCE101");
        Course check = student.searchCourse("CSCE5000");
        assertEquals(oracle, check);
    }

    @Test
    public void testSearchCourseNullCourse()
    {
        Course oracle = courseList.getCourse("CSCE101");
        Course check = null;
        assertEquals(oracle, check);
    }

    @Test
    public void testIsElectiveCompleteNull()
    {
        Boolean actual = student.isElectiveComplete(null);
        assertEquals(false, actual);
    }
    
    @Test
    public void testIsElectiveCompleteLA()
    {
        ArrayList<Course> choices = new ArrayList<Course>(); //empty , expected a result of false bc of this
        Elective liberalArts = new Elective("Liberal Arts", 9, choices);
        boolean actual = student.isElectiveComplete(liberalArts);
        assertEquals(false, actual);
    }

    @Test
    public void testIsElectiveCompleteGFL()
    {
        ArrayList<Course> choices = new ArrayList<Course>(); //empty , expected a result of false bc of this
        Elective gfl = new Elective("GFL", 6, choices);
        boolean actual = student.isElectiveComplete(gfl);
        assertEquals(false, actual);
    }

    @Test
    public void testIsElectiveCompleteSCI()
    {
        ArrayList<Course> choices = new ArrayList<Course>(); //empty , expected a result of false bc of this
        Elective sci = new Elective("SCI", 8, choices);
        boolean actual = student.isElectiveComplete(sci);
        assertEquals(false, actual);
    }

    @Test
    public void testIsElectiveCompleteAIU()
    {
        ArrayList<Course> choices = new ArrayList<Course>(); //empty , expected a result of false bc of this
        Elective aiu = new Elective("AIU", 3, choices);
        boolean actual = student.isElectiveComplete(aiu);
        assertEquals(false, actual);
    }

    @Test
    public void testIsElectiveCompleteGSS()
    {
        ArrayList<Course> choices = new ArrayList<Course>(); //empty , expected a result of false bc of this
        Elective gss = new Elective("GSS", 3, choices);
        boolean actual = student.isElectiveComplete(gss);
        assertEquals(false, actual);
    }

    @Test
    public void testIsElectiveCompleteGHS()
    {
        ArrayList<Course> choices = new ArrayList<Course>(); //empty , expected a result of false bc of this
        Elective ghs = new Elective("GHS", 3, choices);
        boolean actual = student.isElectiveComplete(ghs);
        assertEquals(false, actual);
    }

    @Test
    public void testIsElectiveCompleteCMS()
    {
        ArrayList<Course> choices = new ArrayList<Course>(); //empty , expected a result of false bc of this
        Elective cms = new Elective("CMS", 3, choices);
        boolean actual = student.isElectiveComplete(cms);
        assertEquals(false, actual);
    }
}
