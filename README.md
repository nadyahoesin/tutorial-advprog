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

1. The code quality issues (according to SonarCloud) that I fixed are:

* **Empty try-catch blocks (#1)**

   For the purpose of error handling, the methods to delete and edit a product on `ProductServiceImpl.java` throws an Exception when the input is inappropriate (when the product to be deleted or edited does not exist). 
   The methods on `ProductController.java` that call those methods catch the exceptions and do nothing, which resulted in empty catch blocks. 
   I modified these methods by not using try-catch blocks but instead making the methods throw the Exception as well.


* **Generic Exception class (#2)**

   The methods that I previously mentioned all threw an instance of the `Exception` class. 
   I modified these methods by making them throw an instance of the `NoSuchElementException` class instead, which makes it more clear what causes the Exception.


* **Public Test classes (#3 & #7)**

  Some of the Test classes (e.g. `ProductRepositoryTest` or `CreateProductFunctionalTest`) are previously public. 
  I removed the public modifiers to make these classes have default access modifiers instead.


* **Repeating String constants (#4 & #5)**

  Some of the methods in `ProductController` return the same string to redirect the HTTP request to `list/`. 
  Similarly, some of the methods in `ProductRepository` throws an Exception that contains the same string explaining that the product does not exist.
  Because the same string is used many times, I declared final String variables of these strings instead and made those methods return these variables.


* **No assertion in Test class (#6)**

  Originally, I tested `EshopApplication` just by calling the main function of this class in the Test class (`EshopApplicationTests`).
  I modified this adding an assertion that assert the main function does not throw an exception when it's called.


2. Yes, the current implementation of my CI/CD workflows has met the definition of Continuous Integration and Continuous Deployment.
   Continuous Integration is defined as "a software development practice where continuous changes & updates in codebase are integrated and verified by an automated build script using various tools."
   The current implementation of my workflows met this definition by using the build tool Gradle and running tests, both unit and functional tests, everytime there is a push or pull requests on the master branch.
   Continuous Deployment is when the code is "automatically deployed to the application folder on the specified server" after changes & updates. The current implementation of my workflows ensure this by automatically deploying the application to Koyeb on every push or pull requests on the master branch.

# Module 3 - Maintainability & OO Principles

## Reflection