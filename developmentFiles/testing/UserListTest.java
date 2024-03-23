package testing;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import testClasses.Student;
import testClasses.User;
import testClasses.UserList;
import testClasses.UserType;
import testClasses.WriteFile;




public class UserListTest {
    
    private UserList userList = UserList.getInstance();
    private ArrayList<User> allUsers = userList.getUsers();
    

    @BeforeEach
    public void setup() {
        allUsers.clear();
        allUsers.add(new User(UUID.randomUUID(),"Belon", "Gatsby","belon.gatsby11@email.sc.edu","iLoveMyCat!32","bgatsby",UserType.STUDENT));
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


}
