# Test - Assignment 2

[Assignment description](./assignment-02.pdf)

This assignment consists of sections 1-3 where section 2 should be written individually. The other two sections are written together.

The solution for sections 1 & 3 can be found in this document. The solutions for part two can for each member be found in the [PartTwo](./PartTwo) folder.

## Part 1 - Reflections

### 1.1 Computer Mouse
> _Identify the types of testing you would perform on a computer mouse, to make sure that it is of the highest quality._

```diff
- TODO
```

### 1.2 Catastrophic failure
> _Find a story where a software system defect had a bad outcome. Describe what happened. Can you identify a test that would have prevented it?_

```diff
- TODO
```

## Part 2 - Two katas
This part of the assignment was required to be completed individually. 

The solutions for each member can be found in the [PartTwo](/PartTwo) folder.

## Part 3 - Investigation

### 3.1 JUnit 5
> _Investigate JUnit 5 (Jupiter). Explain the following, and how they are useful._

`@Tag`
- Tag is an annotation that can be applied to both classes and methods to categorize them for the _discovery and execution_ part of the framework. This is useful for not only marking tests but also for filtering them for execution. You could configure JUnit to only run certain tests, [i.e. only tests marked with "acceptance"](https://junit.org/junit5/docs/current/user-guide/#running-tests-build-maven-filter-tags). 
More details can be found in the user guide, such as [this section](https://junit.org/junit5/docs/current/user-guide/#running-tests-tags).

@Tag supports [inheritance/extension for custom types](https://junit.org/junit5/docs/current/user-guide/#writing-tests-meta-annotations). 

**Example:**
```java
@Tag("fast")
@Tag("model")
class TaggingDemo {
...
}
```

```diff
- TODO, actual example from IDE and output window effects
```


`@Disabled`
- In JUnit 4 this was known as _@Ignore_. This is an annotation for both classes and methods which mark them to be skipped for execution.
This is useful for skipping tests that are not fixed now but will be in the future. If the test is no longer relevant it should be removed.

**Example:**
```java
@Disabled("Disabled until bug #99 has been fixed")
class DisabledClassDemo {
...
}
```

`@RepeatedTest`
- This annotation marks a test (that you would normally mark with _@Test_)

`@BeforeEach`, `@AfterEach`
- todo

`@BeforeAll`, `@AfterAll`
- todo

`@DisplayName`
- todo

`@Nested`
- todo

`assumeFalse`, `assumeTrue`
- todo


### 3.2 Mocking frameworks
> _Investigate mocking frameworks for your preferred language. Choose at least two frameworks, and answer the questions._


What are their similarities?
- todo

What are their differences?
- todo

Which one would you prefer, if any, and why?
-todo 


```diff
- TODO
```


## Sources
- https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations
- https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4
