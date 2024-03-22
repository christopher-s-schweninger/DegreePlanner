package testing;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        User user = new User("Gatsby", "Belon", "belon.gatsby11@email.sc.edu", "iLoveMyCat!32", "UserID123", UserType.STUDENT);
         allUsers.add(user);
         WriteFile.writeUser(user);
    }

    @AfterClass
    public void tearDown() {
        
    }

    @Test
    void TestGetUserByEmailAndPassByVaildEmailAndPass() {}

    @Test
    void TestGetUserByEmailAndPassByInvaildemailAndValidPass() {}

    @Test
    void TestGetUserByEmailAndPassByValidEmailAndInvalidPass() {}

    @Test
    void TestGetUserByEmailAndPassByInvalidEmailAndInvaildPass() {}

    @Test

    void TestGetUserByEmailAndPassByEmpatyStringForEmailAndPass() {}

    @Test
    void TestGetUserByEmailAndPassByNullEmailAndPass() {}

}
