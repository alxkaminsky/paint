# Paint

A desktop drawing application built with Java and JavaFX. Draw shapes, freehand squiggles and polylines, pick custom fill and outline colours, then select, move, copy, paste and undo your way to a finished sketch.

<img width="1000" height="702" alt="Paint application screenshot" src="https://github.com/user-attachments/assets/647c0985-d81f-4e39-b2df-91e28e6b0399" />

## Features

**Drawing tools**

| Tool | How to use it |
| --- | --- |
| Circle | Click the centre, drag out the radius, release |
| Oval | Click and drag to size the oval |
| Rectangle / Square | Click one corner, drag to the opposite corner |
| Isosceles / Right Triangle | Click a starting point and drag to size the triangle |
| Squiggle | Click and drag to draw freehand |
| Polyline | Click to place each point; right-click to finish the line |

Every shape shows a live preview while you drag, so you can see exactly what you're about to draw.

**Editing tools**

- **Select** – drag a selection box; anything it touches is selected
- **Move** – drag selected drawables to a new position
- **Bucket Fill** – click a shape to fill it with the current fill colour
- **Cut, Copy, Paste, Delete** – work on the current selection
- **Undo / Redo** – multi-level history covering drawing, moving, pasting, deleting and filling
- **New** – clear the canvas and start fresh

**Styling**

- Separate colour pickers for fill and outline, with full custom colour support
- Line thickness slider (0–20 px)
- The canvas resizes automatically with the window

## Keyboard shortcuts

| Action | Shortcut |
| --- | --- |
| Undo | `Ctrl` + `Z` |
| Redo | `Ctrl` + `Y` |
| Cut | `Ctrl` + `X` |
| Copy | `Ctrl` + `C` |
| Paste | `Ctrl` + `V` |
| Delete selection | `Backspace` |

All of these are also available from the **Edit** menu.

## Getting started

### Requirements

- JDK 22 or newer
- Maven (optional; the included Maven wrapper handles it for you)

### Run from the command line

```bash
git clone https://github.com/alxkaminsky/paint.git
cd paint
./mvnw clean javafx:run        # macOS / Linux
mvnw.cmd clean javafx:run      # Windows
```

### Run from an IDE

Open the project in IntelliJ IDEA (or any IDE with Maven support), let it import the dependencies, and run the `Paint` class in the `paint` package.

## Architecture

The app follows a Model–View–Controller structure, with each view component handling its own input events. Several design patterns keep it modular and easy to extend:

- **Strategy** – each tool (shape drawing, squiggle, polyline, select, move, bucket fill) is its own `ToolStrategy`. The canvas's mouse handler simply delegates to whichever strategy is active, replacing what used to be a ~250-line chain of `if/else` blocks with about two dozen lines.
- **Command** – every user action (draw, move, delete, paste, bucket fill) is a `Command` that knows how to execute and reverse itself. `CommandHistory` keeps undo and redo stacks, which is what makes full multi-level undo/redo possible.
- **Factory** – `ShapeFactory` and `ToolStrategyFactory` centralise object creation, so adding a new shape or tool means touching one place. `ShapeFactory` uses Java's pattern-matching `switch` expressions.
- **Observer** – `PaintModel` notifies the canvas whenever its state changes, and the canvas redraws itself, keeping the model independent of the UI.

Adding a new tool is a matter of writing a `ToolStrategy`, registering it in `ToolStrategyFactory`, and adding a button to `ShapeChooserPanel`.

## Project structure

```
src/main/java/.../paint/
├── Paint.java                  # Application entry point
├── PaintModel.java             # Drawing state (observable)
├── View.java                   # Window, menu bar and layout
├── PaintPanel.java             # Canvas that renders the drawing
├── DrawSettingsPanel.java      # Tool buttons, colour pickers, thickness slider
├── *Strategy.java              # One strategy per tool
├── *Command.java               # Undoable actions + CommandHistory
├── ShapeFactory.java           # Shape creation
└── Circle, Oval, Rectangle, Square, Triangle, Squiggle, Polyline, ...
```

## Built with

- Java 22
- JavaFX 22
- Maven
