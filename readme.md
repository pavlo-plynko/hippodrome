# Hippodrome

Hippodrome is a simple horse race simulation project.  
It is written in Java 17, uses JUnit 5 for testing, and Log4j2 for logging.

## How to Run

Make sure you have Java 17 or higher installed.

1. Clone the repository or download the project files.

2. Open a terminal and navigate to the project folder.

3. Compile and run the Main class:

    ```bash
    javac -d out src/main/java/*.java
    java -cp out Main
    ```

   Alternatively, you can run it directly from your IDE (e.g., IntelliJ IDEA).

## Features

- Horses are created with names, speeds, and distances.
- The race consists of 100 steps, updating every 200 milliseconds.
- Horse progress is displayed in the console.
- The winner — the horse that covers the longest distance — is announced at the end.

## Logging

Log4j2 is used for logging.  
Logs are saved to `logs/hippodrome.log`.  
Every day, a new log file is created in the format: `hippodrome.YYYY-MM-DD.log`.  
Files older than 7 days are automatically deleted.

Log format:

2025-04-28 21:46:43 [main] INFO Main - End of the jumps. Winner: Cherry


Different log levels are implemented:

- **INFO** — for start and end of races.
- **ERROR** — for invalid arguments and validation failures.
- **DEBUG** — for object creation logs (Horse, Hippodrome).

## Testing

JUnit 5 is used for testing.

Covered tests include:

- Constructors validation (null, empty list, invalid values).
- `move()`, `getHorses()`, and `getWinner()` methods.
- Performance test ensuring `Main` finishes within 22 seconds.

Tests can be run via Maven, Gradle, or directly from your IDE.

