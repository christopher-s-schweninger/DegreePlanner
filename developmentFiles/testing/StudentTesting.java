package testing;

import static org.junit.jupiter.api.Assertions.*;

import testClasses.Student;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentTesting 
{
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
    public void testSearchCourseValidCourse()
    {

    }

    @Test
    public void testSearchCourseInvalidCourse()
    {

    }
    
    @Test
    public void testRenderCourseDetailsValidCourse()
    {

    }

    @Test
    public void testRenderCourseDetailsInvalidCourse()
    {

    }

    @Test 
    public void testGetCurrentCourses()
    {

    }

    @Test
    public void testGetCompletedCourses()
    {

    }

    @Test
    public void testGetIncompleteCourses()
    {

    }

    @Test
    public void testGetAllCourses()
    {

    }

    @Test
    public void testIsElectiveCompleteLA()
    {

    }

    @Test
    public void testIsElectiveCompleteGFL()
    {

    }

    @Test
    public void testIsElectiveCompleteSCI()
    {

    }

    @Test
    public void testIsElectiveCompleteAIU()
    {

    }

    @Test
    public void testIsElectiveCompleteGSS()
    {

    }

    @Test
    public void testIsElectiveCompleteGHS()
    {

    }

    @Test
    public void testIsElectiveCompleteCMS()
    {

    }
}
