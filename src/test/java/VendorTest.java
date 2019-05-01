import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendorTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void vendor_instantiatesCorrectly_true() {
        Vendor Vendor= new Vendor("lion",  "vendor");
        assertEquals(true, Vendor instanceof Vendor);
    }

    @Test
    public void VendorInstantiatesWithName_true() throws Exception {
        Vendor Vendor = new Vendor("lion", "vendor");
        assertEquals("lion", Vendor.getName());

    }

    @Test
    public void VendorInstantiatesWithType_true() throws Exception {
        Vendor Vendor = new Vendor("lion",  "vendor");
        assertEquals("vendor", Vendor.getRole());

    }


    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Vendor Vendor = new Vendor("lion",  "vendor");
        Vendor Vendor1 = new Vendor("lion",  "vendor");
        assertTrue(Vendor.equals(Vendor1));
    }

    @Test
    public void save_insertsObjectIntoDatabase_kitenge() {
        Vendor testVendor= new Vendor("lion",  "vendor");
        testVendor.save();
        assertTrue(Vendor.all().get(0).equals(testVendor));
    }

    //return all instances of vendor
    @Test
    public void all_returnsAllInstancesOfVendor_true() {
        Vendor firstVendor = new Vendor("lion",  "vendor");
        firstVendor.save();
        Vendor secondVendor = new Vendor("kangaroo", "vendor");
        secondVendor.save();
        assertEquals(true, Vendor.all().get(0).equals(firstVendor));
        assertEquals(true, Vendor.all().get(1).equals(secondVendor));
    }

    //saving our ids form the db to our classes
    @Test
    public void save_assignsIdToObject() {
        Vendor testVendor= new Vendor("lion", "vendor");
        testVendor.save();
        Vendor savedVendor = Vendor.all().get(0);
        assertEquals(testVendor.getId(), savedVendor.getId());
    }

    //find vendor based on their id
    @Test
    public void find_returnsVendorWithSameId_secondVendor() {
        Vendor firstVendor= new Vendor("camel",  "vendor");
        firstVendor.save();
        Vendor secondVendor = new Vendor("wathog",  "vendor");
        secondVendor.save();
        assertEquals(Vendor.find(secondVendor.getId()), secondVendor);
    }

    //update Vendor
    @Test
    public void update_updatesVendorName_true() {
        Vendor myVendor= new Vendor("lion",  "vendor");
        myVendor.save();
        myVendor.update("elephant");
        assertEquals("elephant", Vendor.find(myVendor.getId()).getName());
    }

    @Test
    public void delete_deletesVendor_true() {
        Vendor myVendor = new Vendor("lion", "vendor");
        myVendor.save();
        int myVendorId = myVendor.getId();
        myVendor.delete();
        assertEquals(null, Vendor.find(myVendorId));
    }

}