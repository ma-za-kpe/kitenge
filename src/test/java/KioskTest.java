import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class KioskTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Kiosk_instantiatesCorrectly_true() {
        Kiosk kiosk= new Kiosk("lion");
        assertEquals(true, kiosk instanceof Kiosk);
    }

    @Test
    public void KioskInstantiatesWithName_true() throws Exception {
        Kiosk Kiosk = new Kiosk("lion");
        assertEquals("lion", Kiosk.getName());

    }

    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Kiosk Kiosk = new Kiosk("lion");
        Kiosk Kiosk1 = new Kiosk("lion");
        assertTrue(Kiosk.equals(Kiosk1));
    }

    @Test
    public void save_insertsObjectIntoDatabase_kitenge() {
        Kiosk testKiosk= new Kiosk("lion");
        testKiosk.save();
        assertTrue(Kiosk.all().get(0).equals(testKiosk));
    }

    //return all instances of Kiosk
    @Test
    public void all_returnsAllInstancesOfKiosk_true() {
        Kiosk firstKiosk = new Kiosk("lion");
        firstKiosk.save();
        Kiosk secondKiosk = new Kiosk("kangaroo");
        secondKiosk.save();
        assertEquals(true, Kiosk.all().get(0).equals(firstKiosk));
        assertEquals(true, Kiosk.all().get(1).equals(secondKiosk));
    }

    //saving our ids form the db to our classes
    @Test
    public void save_assignsIdToObject() {
        Kiosk testKiosk= new Kiosk("lion");
        testKiosk.save();
        Kiosk savedKiosk = Kiosk.all().get(0);
        assertEquals(testKiosk.getId(), savedKiosk.getId());
    }

    //find Kiosk based on their id
    @Test
    public void find_returnsKioskWithSameId_secondKiosk() {
        Kiosk firstKiosk= new Kiosk("camel");
        firstKiosk.save();
        Kiosk secondKiosk = new Kiosk("wathog");
        secondKiosk.save();
        assertEquals(Kiosk.find(secondKiosk.getId()), secondKiosk);
    }

    //update Kiosk
    @Test
    public void update_updatesKioskName_true() {
        Kiosk myKiosk= new Kiosk("lion");
        myKiosk.save();
        myKiosk.update("elephant");
        assertEquals("elephant", Kiosk.find(myKiosk.getId()).getName());
    }

    @Test
    public void delete_deletesKiosk_true() {
        Kiosk myKiosk = new Kiosk("lion");
        myKiosk.save();
        int myKioskId = myKiosk.getId();
        myKiosk.delete();
        assertEquals(null, Kiosk.find(myKioskId));
    }

}