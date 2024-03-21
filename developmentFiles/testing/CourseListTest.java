package testing;
import testClasses.Course;
import testClasses.CourseList;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testNullCourse(){
        Course nullCourse = CourseList.getCourse(null);
        assertEquals(nullCourse, null);
    }


    
}
