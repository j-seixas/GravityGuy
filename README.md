# Gravity Guy - Final Project Delivery:

Project of FEUP's Course [LPOO](https://sigarra.up.pt/feup/pt/ucurr_geral.ficha_uc_view?pv_ocorrencia_id=384940)

**Guided Project**: https://github.com/myownxdeath/feup-lpoo  
**Grade:** 18.7

## **Setup**
* ### Project Setup
To setup the project import it as a gradle project. It should sync all the libraries needed and should work.

* ### App Installation
To install the app run the .apk file in your mobile. You should have the option for "Unkown Sources" in settings to be able to install it. Download it here: https://github.com/myownxdeath/LPOO1617_T3G4_GravityGuy/releases/download/1.0/android-release.apk 
  

## **Project Features**
* Fully-functional Graphical User Interface.  
* Be unit testable.  
* Physics (implemented/using a physics engine(box2D inside libGDX).  
* Mobile (uses gyroscope).  
* Social (using social network (google), to login and interact with players (leaderboards and achievements).  


## **UML Diagram**
  ### 1. Class Diagram Documentation
![UML](https://user-images.githubusercontent.com/19241121/27010688-fcde14c8-4ea1-11e7-8080-b9899ecc7440.png)  
* **EntityModel**: An abstract model representing an entity which belongs to a game model. Stores the position of the model.
* **PlayerModel**: A concrete model representing a player. Stores the state of the player model, used to determine the consequence of inputs.
* **BlockModel**: A concrete model representing a block.
* **GameModel**: A model that represents the game. Stores the player model.
* **EntityBody**: An abstract class that wraps the Body class, the body generated by the concrete classes have a custom BodyDef and Fixture. The concrete classes must implement the methods that generate them.
* **PlayerBody**: A concrete body that represents the player body.
* **BlockBody**: A concrete body that represents the block body.
* **GameController**: Controls the physics engine of the game. Stores the world and player body. Handles the collisions and changes the state of the player accordingly.  
* **PhysicsWorld**: A class that generates the blocks to the world based on a TiledMap.
* **PlayerView**: A view which holds the player' sprites with a certain position and image. The class is responsible for updating the sprite position based on the PlayerModel’s position and for the drawing of the correct sprite.
* **GameView**: A view representing the game screen. Draws all the entity views, controls the camera and handles the game input. Has a HUD.
* **HUD**: Stores and shows the score.
* **GameOverScreen**: A screen to show the score of the run.
* **MenuView**: A view representing the menu screen. Draws all the buttons and handles the menu input.
* **Settings**: A screen for the settings (if it is to play music or not). 
* **LoadingScreen**: A screen for loading the assets.
* **PlayServices**: An abstract class to implement the Google Play Services.
* **GravityGuy**: The game’s main class. It’s responsible for storing some variables needed to all the game and setting the screens.
	
	
### 2. Dynamic Diagram Documentation
![Dynamic Diagram1](https://user-images.githubusercontent.com/19241121/27011846-e07c3fc4-4ebc-11e7-9c26-4a55a385808c.png)  
![Dynamic Diagram2](https://user-images.githubusercontent.com/19241121/27011847-e081561c-4ebc-11e7-96d3-e1a58da8571f.png)
 * **Button Dynamic**  
	When the game is launched, the menu is displayed. The menu has 5 buttons: Play, Settings, Highscores, Achievements and Exit.  
	The play begins a game.
	You can change if you want music or not in Settings.
	It’s possible to check the highest scores achieved by clicking Highscores and see the unlocked achievements in Achievements.  
	A user can exit as soon as the game starts.  
	
* **Gameplay Dynamic**  
	When a level starts, the player is running on a block.   
	While running, the user can click the screen, which will cause the player to invert its gravity, making it free fall to the other side. If the user doesn’t click the screen, the player might eventually run out of block tiles and start to fall (corresponds to the end of a collision).   
	While falling, the user has no control over the player, which makes the game more difficult, since the user must predict where the player is going to end up after clicking. A falling player can return to running state if a collision starts, which means the player hit a block.   
	In both states, the player can go out of bounds, meaning the user lost. If the player lost while running, it means the player was too behind (due to colliding horizontally with a block for too long, making it impossible to move forward without clicking the screen). If the player lost while falling, it means the level had no block tile to keep the player inside the map (which can happen due to miscalculating the trajectory of the player). 

 
## **Design Patterns**  

* **Singleton**: The engines and main class are singletons. This means that GravityGuy, GameModel and GameController will only have one instance which can easily be accessed by each other and by the screens. To implement this, it was decided to use a private member of that class (which starts as null) with a private constructor and a public static getter of the instance (which creates the field if null).  
* **State**: State is used in the way the user input is processed. The input is received by the GameView and processed in the PlayerModel class. The way it processes it is based on a Deterministic Finite Automata. This makes the code a lot cleaner and less susceptible to bugs when compared to using Booleans for every state possible.  
* **Update Method**: Since there is no specific hardware targeted when building crossplatform games, a game can run its main loop a different amount of times when comparing to another platform, simply because of its hardware / operating system performance. To ensure that this phenomenon makes the game smoother instead of faster, the update method used in the render function uses the time elapsed since the last frame. This is helpful because the movement can be made proportional to this delta. In our specific case, this will be used in almost everything: updating an entity’s body’s position and consequently its model and view; updating the camera. This is achieved by multiplying the time delta by the velocity, ensuring that smaller time deltas result in smaller movements.  
* **Template Method**: Because the creation of a Body is always done in a specific order, we've decided to implement the template method design pattern in the EntityBody's constructor, defining the structure of creating a body and letting the concrete classes implement the needed methods. In this case the structure was creating the BodyDef, define a Body based on it and attaching the Model to it. This was achieved by making the createBodyDef() abstract in the superclass and defining it in the concrete classes. In addition, this design pattern is already used by libgdx, since the GravityGuy class extends Game. The main loop is the same in every game. It runs until the user wants to quit, it handles input, updates the models and renders the displayed frame.
* **Double buffer**: The double buffer is one the most used design patterns in video games. If there was only one buffer, flickering would be noticeable since the scene would be rendered in the same buffer that was being displayed. Having two buffers eliminates flickering because, while one is being displayed, the other is being modified. When ready they are swapped. This design pattern is also already implemented in libgdx.  
* **Observer**: The observer pattern is used to check for user input and check for collisions. The first is handled in the GameView class by implementing a ClickListener and its touchdown() method. The second is handled in the GameController class by implementing a ContactListener and its beginContact() and endContact() methods.

## **User Manual**

* **Loading Screen**  
Automatically opens Google Play Sign-In and proceeds for loading all the assets, switching for the Main Menu when finished.  

![Loading1](https://cloud.githubusercontent.com/assets/19241121/26800067/ce7b7ee8-4a2f-11e7-9593-a51eb1c933ec.png)
![Loading2](https://cloud.githubusercontent.com/assets/19241121/26800070/ce858140-4a2f-11e7-9d9e-2b2d967118f2.png)
![Loading3](https://cloud.githubusercontent.com/assets/19241121/26800072/ce99284e-4a2f-11e7-94b1-67acd669d0b9.png)   
  
  
    
* **Main Menu**
    * Play Button – To play the game
    * Settings Button – Goes to the Settings Menu
    * Highscores
    * Achievements
    * Exit Button – Exits the app  
    
![Main Menu](https://cloud.githubusercontent.com/assets/19241121/26800063/ce429380-4a2f-11e7-971a-884114779d0e.png)   
  
  
  
* **Settings Menu**  
To enable or disable the music.  

![Settings Menu](https://cloud.githubusercontent.com/assets/19241121/26800065/ce774f8a-4a2f-11e7-848d-08b474cbf776.png) 


* **HighScores**  
Opens GooglePlay Games HighScores.  

![HighScores](https://cloud.githubusercontent.com/assets/19241121/26800071/ce860fd4-4a2f-11e7-8f94-1d24328f11e5.png) 

  
* **Achievements**   
Opens GooglePlay Games Achievements.    

![Achievements](https://cloud.githubusercontent.com/assets/19241121/26800064/ce6697ee-4a2f-11e7-8fab-910f3c1f008d.png)  
  
  
    
* **Game**   
Tap to change the gravity upside down. You can also change it by using the gyroscope(if your phone has it), rotating in the y-axis.  
You lose if you fall off the map or if you can't keep up with the camera.  

![Achievements](https://cloud.githubusercontent.com/assets/19241121/26800069/ce800b3e-4a2f-11e7-9fc1-e3f6f5214e98.png)

## **Dificulties**  
The main dificulty was integrating the Google Play services into the LibGDX project, since many of the documentation and explanation was old and/or not correct, and the gradle was always giving errors for not matching versions.   

## **Overall Time**
Approximately 120 hours.  

## **Credits**  
For our project we used other people's code and/or assets, and we want to credit them for it:
* https://github.com/playgameservices/android-basic-samples / https://chandruscm.wordpress.com/2015/12/30/how-to-setup-google-play-game-services-in-libgdx-using-android-studio/ - For Google Play Services API
* http://manabreak.eu/java/2016/10/21/unittesting-libgdx.html - For test our project
* https://kenney.nl/assets - Assets (images / sprites)

[![BCH compliance](https://bettercodehub.com/edge/badge/myownxdeath/LPOO1617_T3G4_GravityGuy?branch=master&token=e3e7a18ce6682e511ddb245f7759254a3212c9f9)](https://bettercodehub.com/)
