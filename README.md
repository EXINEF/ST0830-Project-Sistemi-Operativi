# FORMULA 1
Racetrack is a paper and pencil game that simulates a car race, played by two or more players. The game is played on a squared sheet of paper, with a pencil line tracking each car's movement. The rules for moving represent a car with a certain inertia and physical limits on traction, and the resulting line is reminiscent of how real racing cars move. The game requires players to slow down before bends in the track, and requires some foresight and planning for successful play. From https://en.wikipedia.org/wiki/Racetrack_(game)

## How is Developed

The game consists of a circuit and one or more players.
The circuit consists of a set of checkpoints which are of 5 different types:
<br> - NORMAL (BLACK): standard checkpoint
<br> - START (WHITE): start checkpoint
<br> - FINISH (WHITE): end checkpoint
<br> - START_FINISH (WHITE): both start and end checkpoints
<br> - OBLIGATORY (RED): checkpoint that is mandatory to pass used in critical points

The circuit can be linear or circular.
<br> - LINEAR: must have a START, and a FINISH checkpoint
<br> - CIRCULAR: must have a START_FINISH checkpoint

Circuit have a width, and a height to be display bigger or smaller on the GUI.
The Player are Bot, and they can be smart or not, if they are smart they followed the best possible road defined by user, if they are not they go random.
Players have different names and cars, their cars have a color, and a position where they start, this position must be on the start checkpoint, so number of bot is limited by checkpoint start length.
Everyone can create its own circuit linear or circular, it's important to pay attention to make circuit completable by bot, and not too difficult or thick, and save it on a Json File using GSON.

This project use GSON libraries to save game and load game, JSON file contains a Game Object, so it contains all information about Players, Cars and Circuit.

## How To Play It

Build using gradle command:
```bash
gradle build
```

Run Game with:
```bash
gradle run --args="guiType filePath"
```
NOTE WELL: If you want to load default ready game with Circular Circuit and 3 Player (1 smart, 2 no smart) use those scripts:
Using JavaFx GUI use this script:
```bash
gradle run --args="javafx gameCircularConfig.json"
```
Using Prompt GUI use this script:
```bash
gradle run --args="prompt gameCircularConfig.json"
```

The allowed arguments are: 
* `prompt` : to play game with prompt GUI
* `javafx`: to play game with JavaFx GUI
* `filepath`: filePath of Game Config

## Author

* **Alessio Pierdominici** - alessio.pierdominici@studenti.unicam.it

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
