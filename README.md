
## Tasks

1. Understand the existing code, classes and tests so you can make changes. You should do this before planning everything else. It will help you understand what is going on.
    - Student has a class diagram in a digialized format which 
- contains enums, classes, interfaces with all fields, methods
- show connections between classes: inheritance, aggregation, composition
- show multiplicity of connections (1..1, 1..*, *..*)

2. Create a game logic which restricts the movement of the player so s/he cannot run into walls and monsters.
    - The hero is not able to move into walls.
    - The hero is not able to move into monsters.

3. There are items lying around the dungeon. They are visible in the GUI.
    - There are at least 2 item types, for instance a key, and a sword.
    - There can be one item in a map square.
    - A player can stand on the same square as an item.
    - The item has to be displayed on screen (unless somebody stands on the same square)

4. Create a feature that allows the hero to pick up an item.
    - There is a "Pick up" button on the right side of screen.
    - After the player clicks the button, the item the hero is standing on should be gone from map.

5. Show picked up items in the inventory list.
    - There is an `Inventory` list on the screen.
    - After the hero picks up an item, it should appear in inventory.

6. Make the hero to able to attack monsters by moving into them.
    - Attacking a monster removes 5 health points. If health of a monster goes below 0, it dies and disappears.
    - Create a feature where the hero attack a monster, and it doesn't die, it also attacks the hore at the same time (but is a bit weaker, and only removes 2 health).
    - Having a weapon should increase your attack strength.
    - Different monsters have different health and attack strength.

7. Create doors in the dungeon that open by using keys.
    - There are two new square types, closed door, and open door.
    - The hero cannot pass through a closed door, unless it has a key in his/her inventory. Then it becomes an open door.

8. Create three different monster types with different behaviors.
    - There are at least three different monster types with different behaviors.
    - One type of monster does not move at all.
    - One type of monster moves randomly. It cannot go trough walls or open doors.

9. [OPTIONAL] Create a more sophisticated movement AI.
    - One type of monster moves around randomly and teleports to a random free square every few turns.
    - A custom movement logic is implemented (like Ghosts that can move trough walls, monster that chases the player, etc.)

10. Create maps that have more varied scenery. Take a look at the tile sheet (tiles.png). Get inspired!
    - At least three more tiles are used. These can be more monsters, items, or background. At least one of them has to be not purely decorative, but have some effect on gameplay.

11. [OPTIONAL] Allow the player to set a name for my character. This name will also function as a cheat code!
    - There is a `Name` label and text field on the screen.
    - If the name given is one of the game developers' name, the player can walk through walls.

12. Add the possibility to add more levels.
    - There are at least two levels.
    - There is a square type "stairs down". Entering this square moves the player to a different map.

13. Implement bigger levels than the game window.
    - Levels are larger than the game window (for example 3 screens wide and 3 screens tall).
    - When the player moves the player character stays in the center of the view.

# Dungeon Crawl (sprint 2)

## Tasks

1. Create a new sprint from the existing backlog. Last week you had a long list of stories, a few new stories this week.
    - The new items are added to the backlog
    - The team has created a new sprint plan based upon the unified backlog
    - The mandatory "Saving game" backlog item is in Sprint 2 and planned in detail

2. As you will work in a new repository but you need the code from the previous sprint, add the `dungeon-crawl-2` repository as a new remote to the previous sprint's repository, then pull (merge) and push your changes into it.
    - There is a merge commit in the project's repository that contains code from the previous sprint

3. Allow the user to save the current state of the game in a database. Extend the given schema if needed.
    - The application uses PostgreSQL database with the given schema: `schema_ddl.sql`
    - The application respects the `PSQL_USER_NAME`, `PSQL_PASSWORD`, `PSQL_DB_NAME` environment variables
    - Students has an Entity Relationship diagram (connections between classes, 1-1, 1-many...) in a digitalized format.
    - When the user hits `Ctrl+s`, a modal window pops up with one text input field (labelled `Name`) and two buttons, `Save` and `Cancel`.
- When the user clicks on `Save`, the game saves the current state (current map, player' position and content of inventory) in the database
  - If the given name is new then it saves the state
  - If the given username already exist in the db the system shows a dialogbox with a question: `Would you like to overwrite the already existing state?`
    - Choosing `Yes`: the already existing state is updated and all modal window closes
    - Choosing `No`: the dialog closes and the name input field content on the saving dialog is selected again
  - In case of clicking on `Cancel` the saving process terminates without any further action
- The modal window is automatically closed after the operation
    - Already discovered maps are also saved in DB.
    - There is a `Load` menu which brings up a modal window, showing the previously saved states with their names as a selectable list. Choosing an element loads the selected game state with the proper map, position and inventory.

4. Allow the user to export (serialize) his game state into a text file, and load (deserialize) the game from the exported file.
    - There is a menu item with a label `Export game` which triggers the export mechanism
    - The exporting process asks the user for the location and the name of the exported file. The file is created in the defined directory using the given name as a JSON file. eg. `<my-fantastic-game>.json`
    - The file stores every necessary game state information in JSON format.
    - There is a menu button labeled `Import` for importing a previously saved game.
- The system shows a file browser to select an exported file
  - If the chosen file isn't in proper format, the application shows a dialog window (OK, Cancel) with the following message: `IMPORT ERROR! Unfortunately the given file is in wrong format. Please try another one!`
    - If the user clicks on `OK` button then the window closes without any further action
    - If the user click on `Cancel` all dialog and modal window closes
  - In case the file is in the required format, the game loads the state, and navigates on the map to the point where the user left the game with its inventory

5. The customer seeks for quality assurance and wants to see that your code is covered by unit tests. It is important that beyond positive test cases also cover negative scenarios.
    - Every unit test method is well arranged and follows the `arrange`-`act`-`assert` structure
    - Unit test classes and methods follow these naming conventions consistently:
- classes: `<The name of the tested class>Test`
- methods: `<the name of the tested method>_<expected input / tested state>_<expected behavior>`
    - Every test class has at least one negative test case (and more if it's plausible)
    - Code coverage of self-created business logic classes is above 90%
