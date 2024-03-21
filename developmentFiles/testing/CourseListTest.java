package testing;

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
    public void testNullCourses(){
        assertEquals("1", "");
    }
    
}
