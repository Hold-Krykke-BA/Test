# Assignment 4 - Test

## Contributors
- _[Rúni Vedel Niclasen - cph-rn118](https://github.com/Runi-VN)_
- _[Camilla Jenny Valerius Staunstrup - cph-cs340](https://github.com/Castau)_

## [Assignment description](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment4/Assignment4.pdf)
Answer questions about Mockito (with code examples) and complete a TDD exercise. Include three tools (coverage, mutation testing, static analysis).

## Process and stategy
We first experimented with the JSON parser, but changed our mind, as we did not want to create all the code required to fully support the RFC.  
Instead we chose the Tic Tac Toe game.

We started off with the usual TDD strategy for the GameBoard class but found it more relevant to use some void methods for some parts and therefore ended up with some rather untestable methods. In favor of the assignment we turned it around to a more testable version with dependencies and return types. That version can be found in the main branch you are looking at now.

The original clean version can be found [here](https://github.com/Hold-Krykke-BA/Test/blob/d925bcdd6c3f1553f7c0d22dd6dba4d1bf48d380/Assignment4/demo/src/main/java/com/example/demo/GameBoard.java).

### Setup
Install the dependencies using Maven.

**To run our project:**  
1. Install dependencies from POM.xml.
2. Run the main method in `GameBoard.java` to play.
3. Run tests in `GameBoardTest.java`.

The required tools were also added as Maven dependencies.
- JaCoCo
- Pitest
- SpotBugs (Also available as an IntelliJ plugin)

As they are Maven plugins they can be run during lifecycles or on their own.

![image](https://user-images.githubusercontent.com/37186286/143281853-fd377f45-6a98-4fa9-bada-2a5285adb620.png)


## Result

### Part one - Mockito Powerups

- How do you verify that a mock was called?  
Mockito bases its mocking assertions on the `verify` method, which is overloaded for multiple purposes.  
For verifying a mock was called we could simply do as follows: 
    ```java
    @Test
    void testListAdd() {
        List<String> mockList = mock(List.class);
        mockList.add("Hello World");
        verify(mockList).add("Hello World");
    }
    ```
    The verify statement here is actually a shorthand for `verify(foo, times(1))`, so the statement verifies that the `add` function of the mockList was called once.


- How do you verify that a mock was NOT called?  
The `verify` method has the `times(x)` parameter and for testing that a mock was not called you have multiple options:
    ```java
    @Test
    void testListAdd() {
        List<String> mockList = mock(List.class);
        verifyNoMoreInteractions(mockList); //Option one
        verify(mockList, never()).add("Hello World"); //Option two, same as below
        verify(mockList, times(0)).add("Hello World"); //Option three, same as above
    }
    ```
    Mockito themselves state that `verifyNoMoreInteractions` [should not be overused](https://javadoc.io/static/org.mockito/mockito-core/4.1.0/org/mockito/Mockito.html#verifyNoMoreInteractions-java.lang.Object...-) and that option 2-3 are better to use in test methods.


- How do you specify how many times a mock should have been called?  
As previously discussed, we can use `times(x)` to verify the specific amount of times a mock has been called:
    ```java
    @Test
    void testListAdd() {
        List<String> mockList = mock(List.class);
        mockList.add("Hello"); //1
        mockList.add("World"); //2
        verify(mockList, times(2)).add("Hello World"); 
    }
    ```

- How do you verify that a mock was called with specific arguments?  
This is not something we have worked much with, but here are a couple of ways. It is worth noting that the above examples have argument matching built in using `equals()`:

    ```java
    import static org.mockito.ArgumentMatchers.eq; //"Equals" matcher from Mockito
    @Test
    void testListAdd() {
        List<String> mockList = mock(List.class);
        CustomStringImplementation a = "Hello World";
        mockList.add(a);
        verify(mockList).add(eq(a)); //Verify that arguments match according to the equals matcher.
    }
    ```
    This `eq` matcher from Mockito is very powerful and can be utilized across several Mockito use cases.  
    
    We have previously worked with argThat from Mockito which can be powerful when used with lambdas, matchers and more.  
    An example can be found in [our 3rd assignment](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment3/src/test/java/unit/servicelayer/booking/BookingMockTest.java#L33-L50).

    Another way is to use the ArgumentCaptor provided by Mockito as described [in their documentation](https://www.javadoc.io/doc/org.mockito/mockito-core/2.7.9/org/mockito/ArgumentCaptor.html).


- How do you use a predicate to verify the properties of the arguments given to a call to the mock?  
This would be the same way as described above in our 3rd assignment, using `argThat(x -> x.foo == bar)`

### Part two - Tic Tac Toe

As mentioned above we have two versions of the code, one testable and one not so much.

The results are in the `demo` repository here.

#### Code coverage - JaCoCo
Not suprising but some methods could not be tested obviously. These could maybe have been marked to be ignored by JaCoCo?
![image](https://user-images.githubusercontent.com/37186286/143286569-32aa6395-f63b-4502-8a14-994b0807e59a.png)

#### Mutation testing - Pitest

screenshot here

#### Spotbugs
This was used both as a GUI report and as an IntelliJ plugin.

screenshot here

## Reflection
TDD proved useful in regards to creating an overview and insight into the problem at hand.

We wanted to solve the problem with non-testable code (void methods, no direct dependencies/method params) and that caused some issues.

We could have tested more cases - like winning, losing, tying for the win and if dependencies were not created correctly (they always should be, per default, anyways).


## Resources
- https://stackoverflow.com/questions/3555472/mockito-verify-method-arguments
- 
