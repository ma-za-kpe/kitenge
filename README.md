# kitenge (everything African)
Kitenge is an online market, where everything African (such as african jewellery, shoes dresses, toys, cutlery, furniture e.t.c) can be bought and sold across borders.

## GROUP MEMBERS
1. Bill Odida
2. Anthony Ng,ang’a
3. Tom Orenge
4. Maku Mazakpe - TEAM LEADER

## PROBLEM STATEMENT

 East Africa does not have a single market for its arts and crafts, so most people don’t know what’s new and trending in the crafts industry and most crafts sellers have little market share to showcase their items.

## SOLUTION

 Build a solution where people can put their art out there to reach a larger market in East Africa.

## Technologies and frameworks used
1. java 11
2. spark core 2.12
3. Gradle 4.10
4. Spark Template Velocity
5. Junit 4
6. Postgres
7. psql
8. sql2o
9. postgresql

## Usage
1. Clone this repo
2. Compile "gradle compileJava"
3. Run cd to cd build/classes/main `java App`

## User Stories 

## In PSQL:
1. Run psql in terminal
2. CREATE DATABASE kitenge;
3. CREATE TABLE users (id serial PRIMARY KEY, name VARCHAR, role VARCHAR);
4. CREATE DATABASE kitenge_test WITH TEMPLATE kitenge;
5. CREATE TABLE product (id serial PRIMARY KEY, name varchar, price int);
6. DROP DATABASE kitenge_test;
7. CREATE DATABASE kitenge_test WITH TEMPLATE kitenge;
8. ALTER TABLE product ADD COLUMN userId int;
9. CREATE TABLE kiosk (id serial PRIMARY KEY, name VARCHAR);
10. ALTER TABLE kiosk ADD COLUMN userId int;
11. ALTER TABLE product DROP COLUMN imageurl;
12. CREATE TABLE cart (id serial PRIMARY KEY, name varchar, price int);


## ROUTES

1. / (index)
2. /allProducts (get all products)
3. /addProductCart (Add product to cart)
4. /productDetails (get the selected product details)
5. /upVote (upvote a product)
6. /downVote (downvote a product)
7. /addProduct (add product to db)
8. /createKiosk (create a kiosk for product)
9. /updateUser 
10. /updateVendor
11. /updateKiosk
12. /updateProduct
13. /deleteVendor
14. /deleteUser
15. /deleteProduct

## USER STORIES
1. User logs in to view all the products.
2. User adds product to cart to purchase products.
3. User expands product to view details
4. User upvote and downvote products
5. Vendor can view all products
6. Vendor creates a kiosk to put in products
7. Vendor adds products to the kiosk
8. Vendor updates the product details
9. Vendor deletes products
10. Admin views all users
11. Admin views all vendors
12. Admin creates users
13. Admin creates vendors
14. Admin creates kiosks
15. Admin updates user info
16. Admin updates vendor info
17. Admin deletes users
18. Admin deletes vendors


## SCREENSHOTS
## Testing
gradle test

## License
[MIT](https://choosealicense.com/licenses/mit/)
