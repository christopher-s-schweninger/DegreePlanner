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
    //WriteFile.writeUsers();

      //  faculty setup
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

       // WriteFile.writeUsers();
    }
    

    @AfterEach
    public void tearDown() {

        UserList.getInstance().getUsers().clear();
        WriteFile.writeUsers();
        
    }

    @Test
    void testSuccessfulLogout() {
        // Log in first
        userList.login("belon.gatsby11@email.sc.edu", "iLoveMyCat!32");

        // Perform logout
        assertTrue(userList.logout());
        assertNull(userList.user); // Ensure user is null after logout
    }

    @Test
    void testUnsuccessfulLogout() {
        // Ensure no user is logged in
        userList.logout(); // Logout any potential user

        // Perform logout
        assertFalse(userList.logout());
        assertNull(userList.user); // Ensure user is null even after unsuccessful logout
    }

    @Test
    void TestGetUserByEmailAndPassByVaildEmailAndPass() {
        User hasPassAndEmail = userList.getUserByEmailAndPass("belon.gatsby11@email.sc.edu","iLoveMyCat!32");
        assertNotNull(hasPassAndEmail);
        
    }

    @Test
    void TestGetUserByEmailAndPassByInvaildemailAndValidPass() {
        User invalidEmail = userList.getUserByEmailAndPass("UserList.com","iLoveMyCat!32");
        assertNull(invalidEmail);
    }

    @Test
    void TestGetUserByEmailAndPassByValidEmailAndInvalidPass() {
        User invalidPass = userList.getUserByEmailAndPass("belon.gatsby11@email.sc.edu","invalidpassword");
        assertNull(invalidPass);
    }

    @Test
    void TestGetUserByEmailAndPassByInvalidEmailAndInvaildPass() {
        User invalidEmailAndPass = userList.getUserByEmailAndPass("UserList@email.com","invalidpassword");
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
        User user = userList.getUser("UserListID", UserType.STUDENT);
        assertNull(user);
    }

    @Test
    void testGetUserByEmptyUserIDAndEmptyUserType() {
        User user = userList.getUser("", UserType.STUDENT);
        assertNull(user);
    }

    @Test 
    void testGetUserByNullUserIDAndNullUserType() {
        User user = userList.getUser(null, null);
        assertNull(user);
    }


    @Test 

    void testGetUserByNullUserIDAndUserTypeFaculty() {
        User user = userList.getUser(null, UserType.ADVISOR);
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
    void testLoginWithValidFacultyCredentials() {
        User user = userList.login("rmjohnson@sc.edu", "b3stAdvisor0890#");
        assertNotNull(user);
    }

    @Test
    void testLoginWithEmptyCredentials() {
        User user = userList.login("", "");
        assertNull(user);
    }

    @Test

    void testLoginWithNullCredentials() {
        User user = userList.login(null, null);
        assertNull(user);
    }


    @Test
    void testGetUserByEmail() {
        User user = userList.getUserByEmail("belon.gatsby11@email.sc.edu");
        assertNotNull(user);
    }

    @Test

    void testGetUserByEmptyEmail() {
        User user = userList.getUserByEmail("");
        assertNull(user);
    }

    @Test

    void testGetUserByNullEmail() {
        User user = userList.getUserByEmail(null);
        assertNull(user);
    }

    @Test
    void testGetUserByEmailWithInvalidEmail() {
        User user = userList.getUserByEmail("UserList@email.com");
        assertNull(user);
    }
    @Test
    void testGetUserList() {
        HashMap<UUID, User> userListMap = userList.getUserList();
        assertEquals(2, userListMap.size());
        assertTrue(userListMap.containsKey(userList.getUsers().get(0).getUUID()));
        assertTrue(userListMap.containsKey(userList.getUsers().get(1).getUUID()));
    }

    @Test
    void testGetUserEmails() {
        HashMap<String, UUID> userEmails = userList.getUserEmails();
        assertEquals(2, userEmails.size());
        assertTrue(userEmails.containsKey("belon.gatsby11@email.sc.edu"));
        assertTrue(userEmails.containsKey("rmjohnson@sc.edu"));
    }

    @Test
    void testGetUserFullname() {
        HashMap<String, UUID> userFullname = userList.getUserFullname();
        assertEquals(2, userFullname.size());
        assertTrue(userFullname.containsKey("Belon Gatsby"));
        assertTrue(userFullname.containsKey("Rebecca Johnson"));
    }


    //The addUser method can't be tested as it is incompte and needs to be compted to test it.

    // @Test
    // void testAddUser() {
    // }
    @Test
    void testUpdateAdviseStuList_AddingAdvisingStudents() {
        Faculty advisorFaculty = createAdvisorFacultyWithStudents();
        allUsers.add(advisorFaculty);
        userList.updateAdviseStuList();
        assertTrue(advisorFaculty.getAdvisingStudents().size() > 0);
    }

    @Test
    void testUpdateAdviseStuList_NoAdvisingStudents() {
        Faculty advisorFaculty = createAdvisorFacultyWithoutStudents();
        allUsers.add(advisorFaculty);
        userList.updateAdviseStuList();
        assertEquals(0, advisorFaculty.getAdvisingStudents().size());
    }

    @Test
    void testUpdateAdviseStuList_NonAdvisorFaculty() {
        Faculty nonAdvisorFaculty = createNonAdvisorFaculty();
        allUsers.add(nonAdvisorFaculty);
        int initialSize = nonAdvisorFaculty.getAdvisingStudents().size();
        userList.updateAdviseStuList();
        assertEquals(initialSize, nonAdvisorFaculty.getAdvisingStudents().size());
    }

    private Faculty createAdvisorFacultyWithStudents() {
        UUID facultyUUID = UUID.randomUUID();
        String facultyFirstName = "John";
        String facultyLastName = "Doe";
        String facultyEmail = "john.doe@example.com";
        String facultyPass = "password";
        String facultyID = "jdoe";
        UserType facultyType = UserType.ADVISOR;
        HashMap<UUID, User> advisingStudents = new HashMap<>();
        advisingStudents.put(UUID.randomUUID(), new Student(UUID.randomUUID(), "Alice", "Smith", "alice@example.com", "password", "alice", UserType.STUDENT,
                new HashMap<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), 0, 0, new ArrayList<>(), 3.0, true, Major.COMPUTER_SCIENCE));
        return new Faculty(facultyUUID, facultyFirstName, facultyLastName, facultyEmail, facultyPass, facultyID, facultyType, advisingStudents, new HashMap<>());
    }
    
    private Faculty createAdvisorFacultyWithoutStudents() {
        UUID facultyUUID = UUID.randomUUID();
        String facultyFirstName = "Jane";
        String facultyLastName = "Doe";
        String facultyEmail = "jane.doe@example.com";
        String facultyPass = "password";
        String facultyID = "jdoe";
        UserType facultyType = UserType.ADVISOR;
        return new Faculty(facultyUUID, facultyFirstName, facultyLastName, facultyEmail, facultyPass, facultyID, facultyType, new ArrayList<>());
    }
    
    private Faculty createNonAdvisorFaculty() {
        UUID facultyUUID = UUID.randomUUID();
        String facultyFirstName = "Bob";
        String facultyLastName = "Smith";
        String facultyEmail = "bob.smith@example.com";
        String facultyPass = "password";
        String facultyID = "bsmith";
        UserType facultyType = UserType.PROFESSOR;
        return new Faculty(facultyUUID, facultyFirstName, facultyLastName, facultyEmail, facultyPass, facultyID, facultyType, new ArrayList<>());
    }


}
