# Tetris Game

Tetris Game is a Java-based implementation of the classic Tetris puzzle game. Players can rotate and move falling tetrominoes to create and clear horizontal lines, aiming for the highest score possible.

## Getting Started

These instructions will guide you through setting up and running the Tetris game on your local machine.

### Prerequisites

Ensure you have the following installed on your system:

- [Java Development Kit (JDK) 8 or higher](https://www.oracle.com/java/technologies/javase-downloads.html)

### Installing

You can run the Tetris game using one of the following options:

#### Option 1: Run the Precompiled Version
1. Clone the repository:
   ```bash
   git clone https://github.com/Junichi-K/Tetris.git
   ```
2. Navigate to the `dist` directory:
   ```bash
   cd Tetris/dist
   ```
3. Run the precompiled `.jar` file:
   ```bash
   java -jar Tetris.jar
   ```
   Alternatively, double-click the `Tetris.jar` file to launch the game.

#### Option 2: Compile the Source Code
If you'd like to compile the source code yourself:

1. Clone the repository as above.
2. Navigate to the `src` directory:
   ```bash
   cd Tetris/src
   ```
3. Compile the main source file:
   ```bash
   javac tetris/tetris.java
   ```
4. Run the game:
   ```bash
   java tetris.tetris
   ```

#### Note on IDE Usage
If you prefer, you can also import the project into an IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/ide/):
- Open the project as a new Java project.
- Compile and run the `tetris/tetris.java` file directly from the IDE.

## Directory Structure

The project has the following structure:

```
Tetris/
├── build/          # Compiled bytecode and intermediary build files
├── dist/           # Precompiled JAR file
│   └── Tetris.jar
├── nbproject/      # NetBeans project files
├── src/            # Source code files
│   ├── tetris/tetris.java
│   └── ...
├── build.xml       # Build configuration file
├── manifest.mf     # Manifest file for the JAR
└── README.md       # Project documentation
```

## Built With

- [Java](https://www.oracle.com/java/) - The programming language used for the game's logic and implementation.
- [Swing](https://docs.oracle.com/javase/tutorial/uiswing/) - A Java GUI toolkit used to create the user interface, including the game grid, score display, and controls.

## Contributing

Feel free to fork the repository and submit pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Inspired by the classic Tetris game originally designed and programmed by Alexey Pajitnov.
