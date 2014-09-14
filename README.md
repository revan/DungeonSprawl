#DungeonSprawl
####PennApps F2014

This is a simple dungeon crawler game, which is played on any number of Android devices linked via websockets to a host computer.
Each Android device takes a turn representing a room in the dungeon, thus sprawling outward.
The server handles clients joining and leaving in the middle of a game, and larger resolution clients will create larger rooms.

![Two clients](https://raw.github.com/revan/DungeonSprawl/screenshot.png)

## Running
Start the server with `node sprawl.js`, then launch the application on the mobile devices.
Enter the IP address of the server and connect.

## Controls
The server listens for keypresses. WASD for movement.

## Gameplay
Gameplay is simple at this point:

-	You are the `#`.
-	Find and touch `$` to accumulate gold.
-	Find and touch the exit `>` to move to the next room.
-	Rocks `X` are impassable.
-	Zombies `Z` will pursue you if you get too close, and kill you on touch.
-	Line of sight is limited -- outside the radius, nothing is visible.
