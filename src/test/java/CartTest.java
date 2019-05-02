import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Cart_instantiatesCorrectly_true() {
        Cart Cart= new Cart("lion",  100, 1);
        assertEquals(true, Cart instanceof Cart);
    }

    @Test
    public void CartInstantiatesWithName_true() throws Exception {
        Cart Cart = new Cart("lion", 100,  1);
        assertEquals("lion", Cart.getName());

    }

    @Test
    public void CartInstantiatesWithTPrice_true() throws Exception {
        Cart Cart = new Cart("lion",  100,  1);
        assertEquals(100, Cart.getPrice());

    }

    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Cart Cart = new Cart("lion",  100,  1);
        Cart Cart1 = new Cart("lion",  100,  1);
        assertTrue(Cart.equals(Cart1));
    }

    @Test
    public void VendorInstantiatesWithUserId_true() throws Exception {
        Cart Cart = new Cart("lion",  1 ,  1);
        assertEquals(1, Cart.getUserId());
    }


    @Test
    public void save_insertsObjectIntoDatabase_kitenge() {
        Cart testCart= new Cart("lion",  100,  1);
        testCart.save();
        assertTrue(Cart.all().get(0).equals(testCart));
    }

    //return all instances of Cart
    @Test
    public void all_returnsAllInstancesOfCart_true() {
        Cart firstCart = new Cart("lion",  100, 1);
        firstCart.save();
        Cart secondCart = new Cart("kangaroo", 100,  1);
        secondCart.save();
        assertEquals(true, Cart.all().get(0).equals(firstCart));
        assertEquals(true, Cart.all().get(1).equals(secondCart));
    }

    //saving our ids form the db to our classes
    @Test
    public void save_assignsIdToObject() {
        Cart testCart= new Cart("lion", 100,  1);
        testCart.save();
        Cart savedCart = Cart.all().get(0);
        assertEquals(testCart.getId(), savedCart.getId());
    }

    //find Cart based on their id
    @Test
    public void find_returnsCartWithSameId_secondCart() {
        Cart firstCart= new Cart("camel",  100, 1);
        firstCart.save();
        Cart secondCart = new Cart("wathog",  100 , 1);
        secondCart.save();
        assertEquals(Cart.find(secondCart.getId()), secondCart);
    }

    //update Cart
    @Test
    public void update_updatesCartName_true() {
        Cart myCart= new Cart("lion", 100, 1);
        myCart.save();
        myCart.update("elephant");
        assertEquals("elephant", Cart.find(myCart.getId()).getName());
    }

    @Test
    public void delete_deletesCart_true() {
        Cart myCart = new Cart("lion", 100, 1);
        myCart.save();
        int myCartId = myCart.getId();
        myCart.delete();
        assertEquals(null, Cart.find(myCartId));
    }


}