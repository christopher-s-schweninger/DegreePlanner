package testing;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testClasses.Course;
import testClasses.Faculty;
import testClasses.Major;
import testClasses.Student;
import testClasses.User;
import testClasses.UserList;
import testClasses.UserType;
import testClasses.Warnings;
import testClasses.WriteFile;




public class UserListTest {
    
    private UserList userList = UserList.getInstance();
    private ArrayList<User> allUsers = userList.getUsers();
    

    @BeforeEach
    public void setup() {
    allUsers.clear();
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

    Student student = new Student(userUUID, firstName, lastName, userEmail, userPass, userID, userType,
            completedCourses, currentCourses, incompleteCourses, warnings, completedHours, currentHours,
            advisementPlans, GPA, hasScholarships, currMajor);

    allUsers.add(student);
    WriteFile.writeUser(userList);

      // Example faculty setup
        UUID facultyUUID = UUID.randomUUID();
        String facultyFirstName = "Rebecca";
        String facultyLastName = "Johnson";
        String facultyEmail = "rmjohnson@sc.edu";
        String facultyPass = "b3stAdvisor0890#";
        String facultyID = "rjhonson";
        UserType facultyType = UserType.ADVISOR;
        HashMap<UUID, User> advisingStudents = new HashMap<>();
        HashMap<UUID, Course> coursesInstructing = new HashMap<>();
        ArrayList<UUID> advisingStudentsUUIDs = new ArrayList<>();
        advisingStudentsUUIDs.add(UUID.randomUUID()); // Add advising student UUID
        Faculty faculty = new Faculty(facultyUUID, facultyFirstName, facultyLastName, facultyEmail, facultyPass,
                facultyID, facultyType, advisingStudents, coursesInstructing);

        allUsers.add(faculty);

        WriteFile.writeUser(userList);
    }
    

    @AfterClass
    public void tearDown() {

        UserList.getInstance().getUsers().clear();
        WriteFile.writeUser(userList);
        
    }

    @Test
    void TestGetUserByEmailAndPassByVaildEmailAndPass() {
        User hasPassAndEmail = userList.getUserByEmailAndPass("belon.gatsby11@email.sc.edu","iLoveMyCat!32");
        assertNotNull(hasPassAndEmail);
        
    }

    @Test
    void TestGetUserByEmailAndPassByInvaildemailAndValidPass() {
        User invalidEmail = userList.getUserByEmailAndPass("invalid@email.com","iLoveMyCat!32");
        assertNull(invalidEmail);
    }

    @Test
    void TestGetUserByEmailAndPassByValidEmailAndInvalidPass() {
        User invalidPass = userList.getUserByEmailAndPass("belon.gatsby11@email.sc.edu","invalidpassword");
        assertNull(invalidPass);
    }

    @Test
    void TestGetUserByEmailAndPassByInvalidEmailAndInvaildPass() {
        User invalidEmailAndPass = userList.getUserByEmailAndPass("invalid@email.com","invalidpassword");
        assertNull(invalidEmailAndPass);
    }

    @Test

    void TestGetUserByEmailAndPassByEmpatyStringForEmailAndPass() {
        User emptyString = userList.getUserByEmailAndPass("","");
        assertNull(emptyString);
    }

    @Test
    void TestGetUserByEmailAndPassByNullEmailAndPass() {
        User nullEmailAndPass = userList.getUserByEmailAndPass(null, null);
        assertNull(nullEmailAndPass);
    }

    @Test
    void testGetUserByUserIDAndUserType() {
        User user = userList.getUser("bgatsby", UserType.STUDENT);
        assertNotNull(user);
    }

    @Test
    void testGetUserByInvalidUserIDAndUserType() {
        User user = userList.getUser("invaliduserid", UserType.STUDENT);
        assertNull(user);
    }

    @Test
    void testLoginWithValidCredentials() {
        User user = userList.login("belon.gatsby11@email.sc.edu", "iLoveMyCat!32");
        assertNotNull(user);
    }

    @Test
    void testLoginWithInvalidCredentials() {
        User user = userList.login("belon.gatsby11@email.sc.edu", "invalidpassword");
        assertNull(user);
    }

    @Test
    void testGetUserByEmail() {
        User user = userList.getUserByEmail("belon.gatsby11@email.sc.edu");
        assertNotNull(user);
    }

    @Test
    void testGetUserByEmailWithInvalidEmail() {
        User user = userList.getUserByEmail("invalid@email.com");
        assertNull(user);
    }

    @Test
    void testLogout() {
        boolean result = userList.logout();
        assertTrue(result);
    }
}
