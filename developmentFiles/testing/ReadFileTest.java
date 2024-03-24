package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import testClasses.Course;
import testClasses.User;
import testClasses.UserType;
import testClasses.DegreePlan;
import testClasses.AdvisementPlan;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadFileTest {
    private ArrayList<Course> courses;
    private ArrayList<User> users;
    private ArrayList<User> students;
    private ArrayList<User> faculty;
    private ArrayList<DegreePlan> degrees;
    private ArrayList<AdvisementPlan> plans;
    //assertEquals(val1,val2)
	//assertFalse(val)
	//assertTrue(val)
	//assertSame(val1,val2)
	//assertNotSame(val1,val2)
	//assertNull(val)
	//assertNotNull(val)
    
    @BeforeClass
    public void oneTimeSetup()
    {
        courses = ReadFile.readCourses();
        users = ReadFile.readUsers();
        students = ReadFile.readStudents();
        faculty = ReadFile.readFaculty();
        degrees = ReadFile.readDegreePlan();
        plans = ReadFile.readAdvisePlans();
    }

    @AfterClass
    public void oneTimeTearDown()
    {
        courses.clear();
        users.clear();
    }

    @BeforeEach //runs before each test
    public void setup()
    {

    }

    @AfterEach //runs after each test
    public void tearDown()
    {

    }

    @Test
    public void testNotNullReadCourses()
    {
        assertNotNull(courses);
    }

    @Test
    public void testNotNullRandomCourse()
    {
        Random rand = new Random();
        Course temp = courses.get(rand.nextInt(courses.size()));
        assertNotNull(temp);
    }

    @Test
    public void testNoNullCourses()
    {
        boolean nullElement = false;
        for (Course course : courses)
        {
            if (course == null)
                nullElement = true;
        }
        assertFalse(nullElement);
    }

    @Test
    public void testNotNullReadStudents()
    {
        assertNotNull(students);
    }

    @Test
    public void testNotNullRandomStudent()
    {
        Random rand = new Random();
        User temp = students.get(rand.nextInt(students.size()));
        assertNotNull(temp);
    }

    @Test
    public void testNoNullStudents()
    {
        boolean nullElement = false;
        for (User student : students)
        {
            if (student == null)
                nullElement = true;
        }
        assertFalse(nullElement);
    }

    @Test
    public void testOnlyStudentsRead()
    {
        boolean nonStudent = false;
        for (User student : students)
        {
            if (student.getUserType() != UserType.STUDENT)
                nonStudent = true;
        }
        assertFalse(nonStudent);
    }

    @Test
    public void testNotNullReadFaculty()
    {
        assertNotNull(faculty);
    }

    @Test
    public void testNotNullRandomFaculty()
    {
        Random rand = new Random();
        User temp = faculty.get(rand.nextInt(faculty.size()));
        assertNotNull(temp);
    }

    @Test
    public void testNoNullFaculty()
    {
        boolean nullElement = false;
        for (User fac : faculty)
        {
            if (fac == null)
                nullElement = true;
        }
        assertFalse(nullElement);
    }

    @Test
    public void testOnlyFacultyRead()
    {
        boolean nonFaculty = false;
        for (User fac : faculty)
        {
            if (fac.getUserType() != UserType.ADVISOR || fac.getUserType() != UserType.PROFESSOR)
                nonFaculty = true;
        }
        assertFalse(nonFaculty);
    }

}
