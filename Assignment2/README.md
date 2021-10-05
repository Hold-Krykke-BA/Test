# Test - Assignment 2
  
## Contributors
- _[Rúni Vedel Niclasen - cph-rn118](https://github.com/Runi-VN)_
- _[Camilla Jenny Valerius Staunstrup - cph-cs340](https://github.com/Castau)_

## [Assignment description](./assignment-02.pdf)

This assignment consists of sections 1-3 where section 2 should be written individually. The other two sections are written together.

The solution for sections 1 & 3 can be found in this document. The solutions for part two can for each member be found in the [PartTwo](./PartTwo) folder.

## Part 1 - Reflections

### 1.1 Computer Mouse
> _Identify the types of testing you would perform on a computer mouse, to make sure that it is of the highest quality._

* Stress click test for each button on the mouse (including clickable scroll)
* Test of the USB connection
* Test of correct cursor movement when the mouse is moved in all directions
* Test of correct scroll functionality
* Stress test of the scrollwheel in both directions
* Test of multiple inputs at the same time (i.e. click and movement, scroll and movement, click on several buttons at the same time ect.)

### 1.2 Catastrophic failure
> _Find a story where a software system defect had a bad outcome. Describe what happened. Can you identify a test that would have prevented it?_   

The Mars Orbiter that was lost due to a calculations in different units of measurement comes to mind ([from wikipedia](https://en.wikipedia.org/wiki/Mars_Climate_Orbiter#Cause_of_failure)):  
  
> * The Mars Climate Orbiter was a 638-kilogram robotic space probe launched by NASA on December 11, 1998 to study the Martian climate, Martian atmosphere, and surface changes and to act as the communications relay in the Mars Surveyor '98 program for Mars Polar Lander. However, on September 23, 1999, communication with the spacecraft was permanently lost as it went into orbital insertion. The spacecraft encountered Mars on a trajectory that brought it too close to the planet, and it was either destroyed in the atmosphere or escaped the planet's vicinity and entered an orbit around the Sun. An investigation attributed the failure to a measurement mismatch between two software systems: metric units by NASA and US Customary (imperial) units by spacecraft builder Lockheed Martin.
> * On September 8, 1999, Trajectory Correction Maneuver-4 (TCM-4) was computed and then executed on September 15, 1999. It was intended to place the spacecraft at an optimal position for an orbital insertion maneuver that would bring the spacecraft around Mars at an altitude of 226 km on September 23, 1999. However, during the week between TCM-4 and the orbital insertion maneuver, the navigation team indicated the altitude may be much lower than intended at 150 to 170 km. Twenty-four hours prior to orbital insertion, calculations placed the orbiter at an altitude of 110 km; 80 km is the minimum altitude that Mars Climate Orbiter was thought to be capable of surviving during this maneuver. Post-failure calculations showed that the spacecraft was on a trajectory that would have taken the orbiter within 57 km of the surface, where the spacecraft likely skipped violently on the uppermost atmosphere and was either destroyed in the atmosphere or re-entered heliocentric space.
> * The primary cause of this discrepancy was that one piece of ground software supplied by Lockheed Martin produced results in a United States customary unit, contrary to its Software Interface Specification (SIS), while a second system, supplied by NASA, expected those results to be in SI units, in accordance with the SIS. Specifically, software that calculated the total impulse produced by thruster firings produced results in pound-force seconds. The trajectory calculation software then used these results – expected to be in newton-seconds (incorrect by a factor of 4.45) – to update the predicted position of the spacecraft.
> * Still, NASA does not place the responsibility on Lockheed for the mission loss; instead, various officials at NASA have stated that NASA itself was at fault for failing to make the appropriate checks and tests that would have caught the discrepancy.
> * According to NASA, the cost of the mission was $327.6 million total for the orbiter and lander, comprising $193.1 million for spacecraft development, $91.7 million for launching it, and $42.8 million for mission operations.

The mission failure could possibly have been entirely prevented if the following had been implemented:
* Integration test in the system supplied by NASA 
  * Testing specifically for the compliance of the specified functional requirements regarding the units of measurement in the system supplied by Lockheed.
* Unit testing in the system supplied by Lockheed
  * Testing for the units of measurement according to the Software Interface Specification

## Part 2 - Two katas
This part of the assignment was required to be completed individually. 
  
| Made By   | String Utility | Bowling Game   |
|--|:--:|:--:|
| Rúni | [Class](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Runi/src/main/java/katas/one/StringUtil.java) & [Test](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Runi/src/test/java/katas/one/StringUtilTest.java) | [Class](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Runi/src/main/java/katas/two/Game.java) & [Test](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Runi/src/test/java/katas/two/BowlingGameTest.java) |
| Camilla | [Class](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Camillas/src/main/java/StringUtility.java) & [Test](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Camillas/src/test/java/StringUtilityTest.java)| [Class](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Camillas/src/main/java/BowlingGame.java) & [Test](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment2/PartTwo/Camillas/src/test/java/BowlingGameTest.java) |

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
    //...
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
    //...
}
```

`@RepeatedTest`
- This annotation marks a test (that you would normally mark with _@Test_) as a test that should be run a number of times rather than only once. Each invocation of a repeated test behaves like the execution of a regular _@Test_ method.  
The annotation takes a parameter of a single number - the amount of repeats you want.
Optinally you can customize the output with the _name_ parameter. It supports the dynamic placeholders _{currentRepetition}_ and _{totalRepetitions}_ to distinguish between tests. ([Source](https://junit.org/junit5/docs/current/user-guide/#writing-tests-repeated-tests))

**Example:**
```java
@RepeatedTest(10) //Run 10 times
void repeatedTest() {
    // ...
}
```

```diff
- TODO, actual example from IDE and console output + name parameter
```

`@BeforeEach`, `@AfterEach`
- This annotation marks a method that should be run before/after each _@Test_. It can be used to setup and tear down implementation specifics between tests. An example could be the instantiation of a mock collection that could have been "tampered" with by the test before.

`@BeforeAll`, `@AfterAll`
- This annotation marks a static-only method that should be run once, before/after any of the *@Test*s are run. This is useful for initialization and cleanup. An example could be testing with integrations where a one-time setup (and of course a cleanup) is needed.

`@DisplayName`
- Overwrites the custom displayName generated by the output. This is useful if you want more clarity than provided by the test method name, i.e. they follow a standard not as easily distinguishable as the displayName. [Use case examples](https://junit.org/junit5/docs/current/user-guide/#writing-tests-display-names).

`@Nested`
- This annotation marks a progression or change in the current test class. The class may exist within another class or it can be used for grouping classes if there are multiple.
If you test multiple implementations of the same class within a file, this tag can be used to seperate them for clearer and better test reports.  
A good example is available [here](https://junit.org/junit5/docs/current/user-guide/#writing-tests-nested) where the annotation is used in conjunction with classes to display state changes of an implementation.

`assumeFalse`, `assumeTrue`
- These methods are part of the junit.jupiter subset and are known as [Assumptions](https://junit.org/junit5/docs/current/user-guide/#writing-tests-assumptions). They can be used to verify certain elements of the system before continuing with the test. It can also be used to run only certain parts of the test given certain assumptions. Examples available in link above.


### 3.2 Mocking frameworks
> _Investigate mocking frameworks for your preferred language. Choose at least two frameworks, and answer the questions._

For this comparison we chose [Mockito](https://site.mockito.org/) and [EasyMock](https://easymock.org/). We considered [JMockit](https://jmockit.github.io/changes.html) and [jMock](http://jmock.org/) but they have not seen any updates since 2019.

Mockito actually [has their own article on this comparison](https://github.com/mockito/mockito/wiki/Mockito-vs-EasyMock) which is worth a read. It is however from 2014 so some things may have changed. Worth of notice is the second sentence, which states that Mockito actually started off as a fork of EasyMock.

What are their similarities?
- Open-source
- For Java
- Built with TDD and BDD in mind

What are their differences?
- Mockito is licensed under the MIT license, EasyMock under the Apache License.
- Mockito is from ~2007, EasyMock from 2001.
- Mockito follows Given/When/Then, EasyMock follows Given/Expect/When. 
  - This is apparant in EasyMock code where you have to call `Replay()` after `Expect()`. 
  - This is far from Arrange/Act/Assert of JUnit (and the likes).
- Mockito has Hamcrest support out-of-the-box for easy integration with existing codebase.
- If a method of a mocked class has unmocked return data, Mockito handles it by returning clean data (i.e., an empty list). EasyMock instead fails the test as it deems the mock incomplete due to missing data, e.g. a stub.

Which one would you prefer, if any, and why?
- Mockito due to its larger usebase. At a glance its API looks cleaner and easier to use. It seems to be hailed as the superior option. PowerMock has [a Mockito extension](https://github.com/powermock/powermock/wiki/Mockito) to combine their powers.
- I (Rúni) do however prefer the "whitelist" approach of EasyMock, requiring all elements of the mock to match up (i.e., be stubbed) before the test can pass.
- Mockito was built because the team found EasyMock lacking (guess) - and has grown beyond the former. Use that!


_Honorable mention: [PowerMock](https://github.com/powermock/powermock) - the framework that allows you to unit test code normally regarded as untestable._

## Sources
- https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations
- https://junit.org/junit5/docs/current/user-guide/#migrating-from-junit4
- https://junit.org/junit5/docs/current/user-guide/#writing-tests-assumptions
- https://en.wikipedia.org/wiki/List_of_unit_testing_frameworks#Java
- https://stackoverflow.com/questions/3127518/mockito-preferrable-over-easymock
- https://en.wikipedia.org/wiki/Mars_Climate_Orbiter#Cause_of_failure

### Unused sources that may be of interest
- https://dzone.com/articles/best-java-unit-testing-frameworks
- https://www.baeldung.com/mockito-vs-easymock-vs-jmockit
