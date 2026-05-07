# Yahtzee Final Project

This is a multiplayer Yahtzee game built in Java with a Swing GUI.

## Project Scope

- Multiplayer gameplay (1-4 players)
- Turn-based score tracking
- End screen with winner + rankings + sound
- Configurable game settings (dice sides, dice count, rolls per turn)
- Save and continue for in-progress games

## Tech Stack

- Java
- Swing (`javax.swing`)
- File-based data storage (`src/main/resources`)
- Maven build/test/run workflow

## Build and Run

From the project root:

```
mvn compile
mvn exec:java
```

Run all tests:

```
mvn test
```

Create packaged build (`target/`):

```
mvn package
```

Clean build artifacts:

```
mvn clean
```

## How to Play

1. Run the program.
2. Click `PLAY`.
3. Choose number of players.
4. Roll dice, keep/re-roll, and select score lines.
5. Continue until all score lines are filled
6. View winner and rankings on the end screen.
7. Either Exit, Play Again, Or Configure settings.

## Save / Continue

- Use `Save` during gameplay to store current game state.
- On the menu, `Continue` appears when a save exists.
- Selecting `Continue` restores the in-progress multiplayer game.

## Configuration

The config screen edits:

- Dice sides
- Dice in hand
- Rolls per turn

Config setting are stored in:

`src/main/resources/yahtzeeConfig.txt`

## Resource Files

- Save file: `src/main/resources/savegame.txt`
- Per-player scorecards: `src/main/resources/player<id>Scorecard.txt`

## Project Structure

- Main source: `src/main/java/yahtzee`
- Resources/media: `src/main/resources`
- Tests: `src/test/java/yahtzee`
- Generated docs: `docs`
- Build output: `target`

## Notes

- Gameplay is fully GUI-based so no console interaction required for play.
- Entry point is `yahtzee.Yahtzee1`.
