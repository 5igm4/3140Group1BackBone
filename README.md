# project1
Description:
In my game the objective is to doge obstacles and to avoid touching the boundaries of the board. Doing either results in
game over. The twist is, the player gets a random speed boost and must maintain their accuracy as the game progresses.

Controls:
Up Arrow - Move up

Down Arrow - Move down

Right Arrow - Move Right

Left Arrow - Move Left

Works Cited:
http://zetcode.com/tutorials/javagamestutorial/collision/ - taught me how to check for collision
http://zetcode.com/tutorials/javagamestutorial/snake/ - taught me how to check for keyboard events

Methods Changed:
Implemented Bounce method
Implemented Collide
Tick was changed to check for collision and if the player object touches the boundaries
Accel methods were changed to work with bouncing 

Methods Added:
Move was added to give the player object movement and it utilizes the keyboard listener.
Several draw functions were added to reduce the amount of lines needed to create objects/ print text.
shouldBounce was created to determine if the game object should bounce or not.
isGameOver checks if the game should end.
doGameOver actually ends the game.

