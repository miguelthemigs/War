
# War Game in Java

This project implements a War Game using the Model-View-Controller (MVC) architecture in Java. The game features a graphical interface built with Java2D and Java Swing, allowing players to interact with the game visually. Additionally, it includes a suite of JUnit tests to ensure code reliability. 

## Features

- MVC architecture for structured design.
- Graphic Interface using Java2D and Swing.
- Card-based War Game implementation.
- You can save the game state mid-play in your machine
- JUnit tests for thorough code testing.
- Interfaces Singleton, Facade and Observer implemented.

## Gameplay
- You can choose between starting a new game, or picking up a already started match, by a text file in your machine.
![Screenshot 2023-11-30 200456](https://github.com/miguelthemigs/war/assets/93150152/e6791e48-6673-4fa6-aee4-b71f6232162a)

- After the game has started, folowing the rules of the original game, you start to arrange the troops in the countries
![Screenshot 2023-11-30 200832](https://github.com/miguelthemigs/war/assets/93150152/43f68d3f-8e9f-4bd3-89bc-68c27cd00227)

## Atacking
- You attack by selecting the countries that the enemies control. You can only attack a country if you have more than one army stationed there. Through testing methods, you can manipulate the outcome of the dice or roll it randomly in a normal game.
![Screenshot 2023-11-30 201512](https://github.com/miguelthemigs/war/assets/93150152/34c9d768-c94c-4bfb-b70f-29105bd4576d)
![Screenshot 2023-11-30 201554](https://github.com/miguelthemigs/war/assets/93150152/c8d17ddc-7958-4d84-a9f0-b91fed6d7d10)
- If a player has conquered a country, they receive a territory card, which can be used to exchange for new armies.

# Saving and testing
- You can save the game mid-match by creating a log in a text file. Modifying the values in the text file can alter any aspect of the game. For testing purposes, we utilized this method to test the completion of objectives and determine the winner.
