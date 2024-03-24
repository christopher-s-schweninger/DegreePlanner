package testing;
import testClasses.Course;
import testClasses.CourseList;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseListTest {

    @BeforeClass
    public void oneTimeSetup(){}

    @AfterClass
    public void oneTimeTearDown(){}

    @BeforeEach
    public void setup(){}
    
    @AfterEach
    public void tearDown(){}

    @Test
    public void testNullInstance(){
        CourseList nullInstance = CourseList.getInstance();
        assertEquals(nullInstance, null);
    }

    @Test
    public void testSingletonInstance(){
        CourseList firstInstance = CourseList.getInstance();
        CourseList secondInstance = CourseList.getInstance();
        assertSame(firstInstance, secondInstance);
    }

    @Test
    public void testNullCourse(){
        Course nullCourse = CourseList.getCourse(null);
        assertEquals(nullCourse, null);
    }

    @Test
    public void testGetCourse(){
        String validCourseID = "CSCE 101";
        Course course = CourseList.getCourse(validCourseID);
        assertNotNull(course);
        assertEquals(validCourseID, course.getCourseID());
    }

    @Test
    public void testAddNewCourse(){
        //create params for a new course
        UUID newCourseUUID = UUID.fromString("1d526cba-5e85-4b03-91fa-7338aeec8161");
        String newCourseID = "CSCE 342";
        String newCourseName = "Test Course";
        String newCourseDescription = "Testing logic for addCourse() method";
        ArrayList<HashMap<UUID, String>> newCoursePrereqUUID = new ArrayList<>();
        ArrayList<HashMap<UUID, String>> newCourseCoreqUUID = new ArrayList<>();
        int newCourseHours = 3;
        String newRequiredGrade = "C";
        ArrayList<String> newSemesterProvided = new ArrayList<>();
        newSemesterProvided.add("FALL");

        HashMap<UUID, String> prereq = new HashMap<>();
        prereq.put(UUID.fromString("be2fe096-232b-4a88-ae9c-5a172e4f7638"), "Prerequisite");
        newCoursePrereqUUID.add(prereq);

        HashMap<UUID, String> coreq = new HashMap<>();
        coreq.put(UUID.fromString("450da97a-29ae-4de1-a31c-74d59bc48a6e"), "Corequisite");
        newCourseCoreqUUID.add(coreq);

        //tests if course is added successfully
        Course newCourse = new Course(newCourseUUID, newCourseID, newCourseName, newCourseDescription, newCoursePrereqUUID, newCourseCoreqUUID, newCourseHours, newRequiredGrade, newSemesterProvided);
        boolean addResult = CourseList.getInstance().addCourse(newCourse);
        assertTrue(addResult);

        //ensures duplicate course cannot be added
        addResult = CourseList.getInstance().addCourse(newCourse);
        assertFalse(addResult);
    }

    @Test
    public void testGetCourses(){
        ArrayList<Course> courses = CourseList.getInstance().getCourses();
        //list of courses should not be null or empty
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }

    
}
