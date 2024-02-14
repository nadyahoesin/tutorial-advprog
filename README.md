# Module 1 - Coding Standards

## Reflection 1

Clean code principles and secure coding practices (based on the slides) that have been applied to my code are:
* **Meaningful names**
  
  I made sure to name all of my functions and variables with names that clearly explain what the variables are or what the functions do. 
  However, after evaluating my code again, I realised that there are some variables that could've been named better (e.g. `throwAwayProduct` in `ProductController.java` to store the new name or quantity of an edited product).
  

* **Function**

  Following the tutorial and the natural structure of the framework, I wrote some short and simple functions that each have one clear objective.


* **No commented-out codes**

  While writing the code, I changed my code implementations in various places several times. 
  There were times when I commented out codes that I was rewriting. 
  I made sure to delete those codes after I decided to use the newly rewritten codes instead. 


* **Layout and formatting**
 
  I made sure that every code that I wrote, whether it was in Java or HTML file, have a consistent style, indentation, and is consistent in other formatting. 


* **Error handling**

  There were some error handling in `ProductRepository.java` and `ProductServiceImpl.java`. 
  However, the error handling is still minimal and I mostly assume that the function will be supplied with a valid input that won't cause an error to happen.


* **Testing**

  I wrote unit tests that checked both positive and negative scenarios. 
  However, I'm still unsure whether I correctly understand what negative tests are and if my implementations of them are correct.
  I also wrote two functional tests, one that I copied from the tutorial and `CreateProductFunctionalTest.java`.

One thing that could maybe be improved about my source code is adding comments in some places.
However, while writing and evaluating back the code, I don't know where it's appropriate to add comments because every function is relatively short and clear.


## Reflection 2

1. After writing the unit tests, I am a lot more sure of the correctness of the code. 
   However, I'm still not 100% sure because I only wrote unit tests for `Product.java` and `ProductRepository.java`, which is only a small portion of the code. 
   One of the most implementation-heavy classes that I wrote was `ProductController.java`, and there are no tests to verify the correctness of the functions inside that class.
   
   One way to make sure that our unit tests are enough to verify the program is by calculating the code coverage of our tests. 
   100% code coverage means that 100% of functions, statements, branches, conditions, and lines of our code are tested, but it doesn't mean that all the possible inputs and scenarios are accommodated by the code.


2. The new functional test suite could be implemented by modifying the code inside the loop in `CreateProductFunctionalTest.java` to a counter of elements with a `td` tag. 
   Dividing this by 4 will give us the number of items in the product list, since every product created will result in 4 new elements with `td` tags (name, quantity, and the two buttons).
   
   Although this function will be able to correctly verify the number of items in the product list, it is not an elegant or straightforward method to do so.
   It is not clear to anyone else what the reason of implementing the test this way and if someone else were to add more elements with `td` tags outside the product list table, it could make the test incorrect. 
   
   Therefore, the cleanliness of the new functional test suite is worse and the new code reduces the overall code quality.

# Module 2 - CI/CD & DevOps

## Reflection

1. 