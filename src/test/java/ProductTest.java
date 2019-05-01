import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void Product_instantiatesCorrectly_true() {
        Product Product= new Product("lion",  100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        assertEquals(true, Product instanceof Product);
    }

    @Test
    public void ProductInstantiatesWithName_true() throws Exception {
        Product Product = new Product("lion", 100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        assertEquals("lion", Product.getName());

    }

    @Test
    public void ProductInstantiatesWithTPrice_true() throws Exception {
        Product Product = new Product("lion",  100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        assertEquals(100, Product.getPrice());

    }

    @Test
    public void ProductInstantiatesWithTImageUrl_true() throws Exception {
        Product Product = new Product("lion",  100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        assertEquals("https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", Product.getImageUrl());

    }


    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        Product Product = new Product("lion",  100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        Product Product1 = new Product("lion",  100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        assertTrue(Product.equals(Product1));
    }

    @Test
    public void save_insertsObjectIntoDatabase_kitenge() {
        Product testProduct= new Product("lion",  100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        testProduct.save();
        assertTrue(Product.all().get(0).equals(testProduct));
    }

    //return all instances of Product
    @Test
    public void all_returnsAllInstancesOfProduct_true() {
        Product firstProduct = new Product("lion",  100,"https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        firstProduct.save();
        Product secondProduct = new Product("kangaroo", 100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        secondProduct.save();
        assertEquals(true, Product.all().get(0).equals(firstProduct));
        assertEquals(true, Product.all().get(1).equals(secondProduct));
    }

    //saving our ids form the db to our classes
    @Test
    public void save_assignsIdToObject() {
        Product testProduct= new Product("lion", 100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        testProduct.save();
        Product savedProduct = Product.all().get(0);
        assertEquals(testProduct.getId(), savedProduct.getId());
    }

    //find Product based on their id
    @Test
    public void find_returnsProductWithSameId_secondProduct() {
        Product firstProduct= new Product("camel",  100, "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png",1);
        firstProduct.save();
        Product secondProduct = new Product("wathog",  100,"https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        secondProduct.save();
        assertEquals(Product.find(secondProduct.getId()), secondProduct);
    }

    //update Product
    @Test
    public void update_updatesProductName_true() {
        Product myProduct= new Product("lion", 100,  "https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        myProduct.save();
        myProduct.update("elephant");
        assertEquals("elephant", Product.find(myProduct.getId()).getName());
    }

    @Test
    public void delete_deletesProduct_true() {
        Product myProduct = new Product("lion", 100,"https://i.postimg.cc/gk31T7FP/Screenshot-from-2019-04-30-08-59-32.png", 1);
        myProduct.save();
        int myProductId = myProduct.getId();
        myProduct.delete();
        assertEquals(null, Product.find(myProductId));
    }

}