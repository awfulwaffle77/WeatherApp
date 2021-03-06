# Yet Another Weather App

## Short Intro

A JavaFX GUI Application written in (**YOU GUESSED IT!**) Java. The countries and cities are read from the file in 
`resources/cities.txt` as requested.

![Application Image](https://i.imgur.com/JA0kR4v.jpg)

## Diagrams

#### Classes diagram
![Diagram 1](https://i.imgur.com/R6VhSvE.jpg)

#### System Diagram 
![Diagram 2](https://i.imgur.com/TAirxzO.jpg)


## How to set up

I have used [this link](https://openjfx.io/openjfx-docs/), JavaFx and IntelliJ -> Non-Modular from IDE

Due to the implementation, the files in the `dependencies` folder must be manually added in the project like so: 

In IntteliJ: File->Project Structure->Modules->"+"->JARs or directories, add the .jar files, then select all of them and click Ok.


## Things to know

- Maven was not used in the development of this application, because I wanted to get more familiar with .jars and IntelliJ.

- The project respects the MVC architecture

- In the first commit, the API key was deleted from source, but I have later added it, because you shouldn't bother with 
getting an API Key. 

- The dependencies(the .jar files) are stored in the dependencies folder

- Due to an issue with SceneBuilder, I had to manually add the font. It is located in `resoruces/fonts` and 
`styles.css` is needed to load the font.

## Tests

Reading further on the topic of tests, I have realised that my class implementation had no possibility of using
`@InjectMocks` decorators in testing, due to the fact that the only class that was including others was `Controller` and
it was only communicating with the UI, making me unable to test it.

### JUnit

For the unit testing part, I have picked one of the few independent classes and the only one that does something fancy
from those, that is the `Parser` class. There were 2 tests made: 

1. Random fixed input tests(where input differs from the one actually used in the app to see if it is viable)
2. Exception throwing tests where, given a broken `jsonString`, we expect a certain Exception type and message

### Mocking 

For the Mocking part, the choice was obvious, but hard to test. The tested class was `Controller` due to the fact that
it was the only one including several other classes. The hard part was that due to the fact that it directly 
communicated with the View component, there were few functionalities to test, but I tried to make the test as
exhaustive as possible regarding the use of different functions so as to show that the usage of Mockito was fully 
understood. The test is as follows:

1. Was made to play with the more complex functionalities of Mockito. Due to the fact that creating a JSON as is the 
one from the GET request is pretty difficult and doesn't represent a priority of this project, I used some predefined 
variables.
