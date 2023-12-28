# Dice Games
* **Activity lifecycle**
* **Intents**
* **Persisting the UI state**
* **Developing UI for landscape and portrait mode**

---
## Version of Android Studio Used
Android Studio Giraffe|2022.3.1 Patch 1

---
## Project Description and Development Details
**Project Name:** Dice Games App

---
### App Description
This app consists of two basic dice games. The first game is a luck-based game. You need to roll a die, and if you get a 6, you will earn 5 coins for it. You may try this as many times as you want. The coins you earn will be reflected above as your balance.
The second game is a gambling game. It is based on a high-risk high-return phenomenon. The rules for this game can be viewed from the info button at the bottom of the screen.
The known bugs for this app is that it might crash when a user enters a value greater than INT_MAX value which is not possible because the balance itself can be at max an integer.

---
### App Screenshots
<table>
  <tr>
    <td><img src="https://github.com/Nandish02/Dice-Games/assets/94218870/bb4fd785-5a94-4ebe-8284-599a93905f43" alt="dicegames_1stpage"></td>
    <td><img src="https://github.com/Nandish02/Dice-Games/assets/94218870/a9a3c965-5828-44a4-9b8c-a6ba68cc9b9e" alt="dicegames_2ndpage"></td>
    <td><img src="https://github.com/Nandish02/Dice-Games/assets/94218870/37836daa-47e9-462d-8a25-d25d3b31720b" alt="dicegames_infopage"></td>
  </tr>
</table>

---
### Development Details
* The first task was to design a model for the `WalletViewModel` class which can be used to implement the `WalletActivity.java` file.
    - Firstly, methods like `balance()`, `setBalance()`, `rollDie()`, `dieValue()`, `setDie()`, `setIncrement()`, and `setWinValue()` were implemented.
    - `Die6` class using the `Die` interface for some of the functions was implemented.
    - After finishing this, it was wired up with the button in the XML file.
    - The first button is the roll die button for which I just called the roll function from the model and set the values of die and coins respectively.
    - The second button is used to guide us to the second game, and I used explicit intents to invoke two or more activity classes.

* To implement `TwoOrMoreViewModel`, the following functions were implemented. Below is the description of how it was implemented:
    1. `balance()`: return the balance field in the class.
    2. `setBalance()`: set the balance field to the parameter value provided in the function.
    3. `gameType()` and `setGameType()`: getter and setter methods for GameType.
    4. `wager()` and `setWager()`: getter and setter methods for Wager.
    5. `isValidWager()`: used if-else statements to check whether the amount that can be lost based on the wager value entered is greater than the available balance.
    6. `diceValues()`: set the dice values in a list and return that list of present in the class.
    7. `addDie()`: to add a die to the list of dice which is present as a field in the class.
    8. `play()`: Firstly, all the illegal state exceptions due to which the game couldn't be played were handled.
    - Then a hashmap was used to find the maximum number of alike in all possible values of die, and if it was greater than the game type selected, it resulted in a win, else a loss.

* After finishing the model, the design was implemented for `Two or more` activity.
    - The first thing was to extract the coin balance from the previous activity using the `genIntentExtra` method.
    - Then to check which radio button is toggled by the user and accordingly set the game type.
    - Then to extract the value entered by the user in the wager edittext and accordingly set the wager.
    - Then to implement the onclick activity for Go button which used various methods from the model such as `play()` and `isValidWager()` to display the appropriate result.

* Explicit intent to implement the back button was done, which transfers data from one activity to another using the `putExtra()` and `getIntExtra()` functions.
    - The Android back button was also implemented which does the same job by using the `onBackPressed()` method.
    - The back button guides to the older game maintaining the same coins balance across the two activities.

* The Info button in `TwoOrMoreActivity` is used to open a new Activity so a new Activity named `Info` was added.
    - By using intents, the `InfoActivity` was launched that shows the rules of the `Two or more` game.
    - A simple text view was used to display the rules of the game which are stored in a string in `string.xml` file.
    - The default back button of Android in `InfoActivity` takes you back to a saved state in `TwoOrMoreActivity`.

* The landscape UI had to be recreated, with all components remaining the same as the initial portrait XML.
    - The design had to be altered to make it more appealing and to ensure that all of the features were clearly apparent.
    - Over the course of rotation, all the elements were retained.
    - A saved state bundle was used to solve this.

---
## Testing approach
- A test-driven approach was followed for handling all the exceptions and used the logic discussed in the lectures to implement the remaining classes.
- The code passed all the given test cases for four different classes.
- UI instrumented tests were written which were integrated with espresso to check accessibility for the app.
- Separate test files for each activity in the app and wrote instrumented test cases for each.
- JUnit tests were also written for each activity.
