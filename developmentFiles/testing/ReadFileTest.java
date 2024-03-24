package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import testClasses.Course;
import testClasses.User;
import testClasses.UserType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadFileTest {
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
        
    }

    @AfterClass
    public void oneTimeTearDown()
    {

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
        ArrayList<Course> courses = ReadFile.readCourses();
        assertNotNull(courses);
    }

    @Test
    public void testNotNullRandomCourse()
    {
        Random rand = new Random();
        ArrayList<Course> courses = ReadFile.readCourses();
        Course temp = courses.get(rand.nextInt(courses.size()));
        assertNotNull(temp);
    }

    @Test
    public void testNoNullCourses()
    {
        ArrayList<Course> courses = ReadFile.readCourses();
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
        ArrayList<User> students = ReadFile.readStudents();
        assertNotNull(students);
    }

    @Test
    public void testNotNullRandomStudent()
    {
        Random rand = new Random();
        ArrayList<User> students = ReadFile.readStudents();
        User temp = students.get(rand.nextInt(students.size()));
        assertNotNull(temp);
    }

    @Test
    public void testNoNullStudents()
    {
        ArrayList<User> students = ReadFile.readStudents();
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
        ArrayList<User> students = ReadFile.readStudents();
        boolean nonStudent = false;
        for (User student : students)
        {
            if (student.getUserType() != UserType.STUDENT)
                nonStudent = true;
        }
        assertFalse(nonStudent);
    }

}
