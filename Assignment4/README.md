# Assignment 4 - Test

## Contributors
- _[Rúni Vedel Niclasen - cph-rn118](https://github.com/Runi-VN)_
- _[Camilla Jenny Valerius Staunstrup - cph-cs340](https://github.com/Castau)_

## [Assignment description](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment4/Assignment4.pdf)
```diff
-TODO
```

## Process and stategy
```diff
-TODO
```

### Setup
```diff
-TODO
```

**To run our project:**  
```diff
-TODO
```

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


## Reflection
```diff
-TODO
```
## Resources
- https://stackoverflow.com/questions/3555472/mockito-verify-method-arguments
- 