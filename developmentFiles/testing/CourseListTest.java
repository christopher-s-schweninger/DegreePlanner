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

    //CourseList's state should already be reset before each test
    @BeforeClass
    public void oneTimeSetup(){}

    @AfterClass
    public void oneTimeTearDown(){}

    @BeforeEach
    public void setup(){}
    
    @AfterEach
    public void tearDown(){}

    @Test
    public void testSingletonInstance(){
        CourseList firstInstance = CourseList.getInstance();
        CourseList secondInstance = CourseList.getInstance();

        assertSame(firstInstance, secondInstance);
    }

    //should we have reset instance method?
    @Test
    public void testResetInstance(){
        CourseList originalInstance = CourseList.getInstance();
        originalInstance.addCourse(new Course(UUID.randomUUID(), "CSCE 123", "Course 123", "Course Description", new ArrayList<>(), new ArrayList<>(), 3, "C", new ArrayList<>()));
        assertFalse(originalInstance.getCourses().isEmpty());

        CourseList.resetInstance();
        CourseList newInstance = CourseList.getInstance();
        assertTrue(newInstance.getCourses().isEmpty());
        assertNotSame(originalInstance, newInstance);
    }

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
    public void testGetCourseIDEmptyList(){
        CourseList.resetInstance();
        String nonExistentCourseID = "CSCE 123";
        Course result = CourseList.getCourse(nonExistentCourseID);

        assertNull(result);
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
    public void testGetCourseUUIDEmptyList(){
        CourseList.resetInstance();
        String nonExistentCourseUUID = "non-existent UUID";
        Course result = CourseList.getCourse(nonExistentCourseUUID);

        assertNull(result);
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
    public void testAddNullCourse() {
        boolean result = CourseList.getInstance().addCourse(null);
        
        assertFalse(result);
    }

    @Test
    public void testAddExistingCourseUUID(){
        UUID duplicateUUID = UUID.fromString("7ef722de-3eb8-4136-8de4-6fd697b1375f");

        Course firstCourse = new Course(duplicateUUID, "CSCE 342", "First Course", "First Course Description", new ArrayList<>(), new ArrayList<>(), 3, "C", new ArrayList<>());
        boolean firstAddResult = CourseList.getInstance().addCourse(firstCourse);
        assertTrue(firstAddResult);

        Course secondCourse = new Course(duplicateUUID, "CSCE 343", "Second Course", "Second Course Description", new ArrayList<>(), new ArrayList<>(), 3, "C", new ArrayList<>());
        boolean secondAddResult = CourseList.getInstance().addCourse(secondCourse);
        assertFalse(secondAddResult);
    }

    @Test
    public void testAddExistingCourseID() {
        Course firstCourse = new Course(UUID.randomUUID(), "CSCE 342", "First Course", "First Course Description", new ArrayList<>(), new ArrayList<>(), 3, "C", new ArrayList<>());
        boolean firstAddResult = CourseList.getInstance().addCourse(firstCourse);
        assertTrue(firstAddResult);
    
        Course secondCourse = new Course(UUID.randomUUID(), "CSCE 342", "Second Course", "Second Course Description", new ArrayList<>(), new ArrayList<>(), 3, "C", new ArrayList<>());
        boolean secondAddResult = CourseList.getInstance().addCourse(secondCourse);
        assertFalse(secondAddResult);
    }

    @Test
    public void testGetCoursesNotEmpty(){
        ArrayList<Course> courses = CourseList.getInstance().getCourses();
        //list of courses should not be null or empty
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
    }

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

    @Test
    public void testEditNonExistingCourse(){
        CourseList courseList = CourseList.getInstance();
        String nonExistingCourseID = "dummyID";
        UserType userType = UserType.ADVISOR;
        String newName = "new name";
        String newDescription = "new description";

        boolean result = courseList.editCourse(nonExistingCourseID, userType, newName, newDescription);
        assertFalse(result);
    }

}
