import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommonUserTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void CommonUser_instantiatesCorrectly_true() {
        CommonUser CommonUser= new CommonUser("lion",  "commonUser");
        assertEquals(true, CommonUser instanceof CommonUser);
    }

    @Test
    public void CommonUserInstantiatesWithName_true() throws Exception {
        CommonUser CommonUser = new CommonUser("lion", "CommonUser");
        assertEquals("lion", CommonUser.getName());

    }

    @Test
    public void CommonUserInstantiatesWithRole_true() throws Exception {
        CommonUser CommonUser = new CommonUser("lion",  "commonUser");
        assertEquals("commonUser", CommonUser.getRole());

    }


    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        CommonUser CommonUser = new CommonUser("lion",  "commonUser");
        CommonUser CommonUser1 = new CommonUser("lion",  "commonUser");
        assertTrue(CommonUser.equals(CommonUser1));
    }

    @Test
    public void save_insertsObjectIntoDatabase_kitenge() {
        CommonUser testCommonUser= new CommonUser("lion",  "commonUser");
        testCommonUser.save();
        assertTrue(CommonUser.all().get(0).equals(testCommonUser));
    }

    //return all instances of CommonUser
    @Test
    public void all_returnsAllInstancesOfCommonUser_true() {
        CommonUser firstCommonUser = new CommonUser("lion",  "commonUser");
        firstCommonUser.save();
        CommonUser secondCommonUser = new CommonUser("kangaroo", "commonUser");
        secondCommonUser.save();
        assertEquals(true, CommonUser.all().get(0).equals(firstCommonUser));
        assertEquals(true, CommonUser.all().get(1).equals(secondCommonUser));
    }

    //saving our ids form the db to our classes
    @Test
    public void save_assignsIdToObject() {
        CommonUser testCommonUser= new CommonUser("lion", "commonUser");
        testCommonUser.save();
        CommonUser savedCommonUser = CommonUser.all().get(0);
        assertEquals(testCommonUser.getId(), savedCommonUser.getId());
    }

    //find CommonUser based on their id
    @Test
    public void find_returnsCommonUserWithSameId_secondCommonUser() {
        CommonUser firstCommonUser= new CommonUser("camel",  "commonUser");
        firstCommonUser.save();
        CommonUser secondCommonUser = new CommonUser("wathog",  "commonUser");
        secondCommonUser.save();
        assertEquals(CommonUser.find(secondCommonUser.getId()), secondCommonUser);
    }

    //update CommonUser
    @Test
    public void update_updatesCommonUserName_true() {
        CommonUser myCommonUser= new CommonUser("lion",  "commonUser");
        myCommonUser.save();
        myCommonUser.update("elephant");
        assertEquals("elephant", CommonUser.find(myCommonUser.getId()).getName());
    }

    @Test
    public void delete_deletesCommonUser_true() {
        CommonUser myCommonUser = new CommonUser("lion", "commonUser");
        myCommonUser.save();
        int myCommonUserId = myCommonUser.getId();
        myCommonUser.delete();
        assertEquals(null, CommonUser.find(myCommonUserId));
    }


}