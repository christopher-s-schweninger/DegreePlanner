package testing;
import testClasses.Course;
import testClasses.CourseList;
import testClasses.UserType;

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

    // @Test
    // public void testNullInstance(){
    //     CourseList nullInstance = CourseList.getInstance();
    //     assertEquals(nullInstance, null);
    // }

    @Test
    public void testSingletonInstance(){
        CourseList firstInstance = CourseList.getInstance();
        CourseList secondInstance = CourseList.getInstance();
        assertSame(firstInstance, secondInstance);
    }

    // @Test
    // public void testNullCourse(){
    //     Course nullCourse = CourseList.getCourse(null);
    //     assertEquals(nullCourse, null);
    // }

    @Test
    public void testGetCourseByValidID(){
        String validCourseID = "CSCE 101";
        Course course = CourseList.getCourse(validCourseID);
        assertNotNull(course);
        assertEquals(validCourseID, course.getCourseID());
    }

    @Test
    public void testGetCourseByInvalidID(){
        String invalidCourseID = "CSCE 999";
        Course course = CourseList.getCourse(invalidCourseID);
        assertNull(course);
    }

    @Test
    public void testGetCourseByValidUUID(){
        UUID validCourseUUID = UUID.fromString("0ee3fc1c-ec19-4637-aeb5-614577b6d22f");
        Course course = CourseList.getCourseByUUID(validCourseUUID);
        assertNotNull(course);
        assertEquals(validCourseUUID, course.getCourseUUID());
    }

    @Test
    public void testGetCourseByInvalidUUID(){
        UUID invalidCourseUUID = UUID.fromString("invalid_UUID");
        Course course = CourseList.getCourseByUUID(invalidCourseUUID);
        assertNull(course);
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
    public void testGetCoursesNotEmpty(){
        ArrayList<Course> courses = CourseList.getInstance().getCourses();
        //list of courses should not be null or empty
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }

    // @Test
    // public void testGetCoursesEmptyState(){
    //     ArrayList<Course> courses = CourseList.getInstance().getCourses();
    //     assertTrue(courses.isEmpty());
    // }

    @Test
    public void testEditCourseAsAdvisor(){
        //ensure course exists
        CourseList courseList = CourseList.getInstance();
        String courseID = "CSCE 145";
        UserType userType = UserType.ADVISOR;
        String newCourseName = "Test Course";
        String newDescription = "Testing editCourse() method for Advisor";

        Course existingCourse = CourseList.getCourse(courseID);
        assertNotNull(existingCourse);

        boolean editResult = courseList.editCourse(courseID, userType, newCourseName, newDescription);
        assertTrue(editResult);

        Course editedCourse = CourseList.getCourse(courseID);
        assertNotNull(editedCourse);
        assertEquals(newCourseName, editedCourse.getCourseName());
        assertEquals(newDescription, editedCourse.getCourseDescription());
    }

    @Test
    public void testEditCourseAsProfessor(){
        //ensure course exists
        CourseList courseList = CourseList.getInstance();
        String courseID = "CSCE 102";
        UserType userType = UserType.PROFESSOR;
        String newCourseName = "Test Course";
        String newDescription = "Testing editCourse() method for Professor";

        Course existingCourse = CourseList.getCourse(courseID);
        assertNotNull(existingCourse);

        boolean editResult = courseList.editCourse(courseID, userType, newCourseName, newDescription);
        assertTrue(editResult);

        Course editedCourse = CourseList.getCourse(courseID);
        assertNotNull(editedCourse);
        assertEquals(newCourseName, editedCourse.getCourseName());
        assertEquals(newDescription, editedCourse.getCourseDescription());
    }

    @Test
    public void testEditCourseUnauthorizedUser(){
        CourseList courseList = CourseList.getInstance();
        String courseID = "CSCE 145";
        UserType userType = UserType.STUDENT; 
        String newCourseName = "Unchanged Course";
        String newDescription = "Should not change";

        boolean editResult = courseList.editCourse(courseID, userType, newCourseName, newDescription);
        assertFalse(editResult);
        
        //ensure that the course remains unchanged
        Course uneditedCourse = CourseList.getCourse(courseID);
        assertNotEquals(newCourseName, uneditedCourse.getCourseName());
        assertNotEquals(newDescription, uneditedCourse.getCourseDescription());
    }

}
