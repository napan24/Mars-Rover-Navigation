import java.util.Scanner;

// Define the Position class
class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

// Define the Grid class
class Grid {
    private int width;
    private int height;
    private boolean[][] obstacles;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.obstacles = new boolean[width][height];
    }

    public boolean isObstacle(int x, int y) {
        return obstacles[x][y];
    }

    public void setObstacle(int x, int y) {
        obstacles[x][y] = true;
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}

// Define the Direction interface and its implementations
interface Direction {
    void moveForward(Rover rover);
    Direction turnLeft();
    Direction turnRight();
}

class North implements Direction {
    public void moveForward(Rover rover) {
        Position position = rover.getPosition();
        Grid grid = rover.getGrid();
        int newX = position.getX();
        int newY = position.getY() + 1;
        if (grid.isWithinBounds(newX, newY) && !grid.isObstacle(newX, newY)) {
            rover.setPosition(new Position(newX, newY));
        }
    }

    public Direction turnLeft() {
        return new West();
    }

    public Direction turnRight() {
        return new East();
    }
}

class South implements Direction {
    public void moveForward(Rover rover) {
        Position position = rover.getPosition();
        Grid grid = rover.getGrid();
        int newX = position.getX();
        int newY = position.getY() - 1;
        if (grid.isWithinBounds(newX, newY) && !grid.isObstacle(newX, newY)) {
            rover.setPosition(new Position(newX, newY));
        }
    }

    public Direction turnLeft() {
        return new East();
    }

    public Direction turnRight() {
        return new West();
    }
}

class East implements Direction {
    public void moveForward(Rover rover) {
        Position position = rover.getPosition();
        Grid grid = rover.getGrid();
        int newX = position.getX() + 1;
        int newY = position.getY();
        if (grid.isWithinBounds(newX, newY) && !grid.isObstacle(newX, newY)) {
            rover.setPosition(new Position(newX, newY));
        }
    }

    public Direction turnLeft() {
        return new North();
    }

    public Direction turnRight() {
        return new South();
    }
}

class West implements Direction {
    public void moveForward(Rover rover) {
        Position position = rover.getPosition();
        Grid grid = rover.getGrid();
        int newX = position.getX() - 1;
        int newY = position.getY();
        if (grid.isWithinBounds(newX, newY) && !grid.isObstacle(newX, newY)) {
            rover.setPosition(new Position(newX, newY));
        }
    }

    public Direction turnLeft() {
        return new South();
    }

    public Direction turnRight() {
        return new North();
    }
}

// Define the Command interface and its implementations
interface Command {
    void execute(Rover rover);
}

class MoveForwardCommand implements Command {
    public void execute(Rover rover) {
        rover.getDirection().moveForward(rover);
    }
}

class TurnLeftCommand implements Command {
    public void execute(Rover rover) {
        rover.setDirection(rover.getDirection().turnLeft());
    }
}

class TurnRightCommand implements Command {
    public void execute(Rover rover) {
        rover.setDirection(rover.getDirection().turnRight());
    }
}

// Define the Rover class
class Rover {
    private Position position;
    private Direction direction;
    private Grid grid;

    public Rover(Position position, Direction direction, Grid grid) {
        this.position = position;
        this.direction = direction;
        this.grid = grid;
    }

    public void executeCommand(Command command) {
        command.execute(this);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Grid getGrid() {
        return grid;
    }
}

// Putting it all together in the MarsRoverSimulation class
public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        Position position = new Position(0, 0);
        Direction direction = new North();
        Rover rover = new Rover(position, direction, grid);

        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Enter command (F for Forward, L for Left, R for Right, Q for Quit): ");
            command = scanner.nextLine().toUpperCase();

            if (command.equals("Q")) {
                break;
            }

            switch (command) {
                case "F":
                    rover.executeCommand(new MoveForwardCommand());
                    break;
                case "L":
                    rover.executeCommand(new TurnLeftCommand());
                    break;
                case "R":
                    rover.executeCommand(new TurnRightCommand());
                    break;
                default:
                    System.out.println("Invalid command. Please enter F, L, R, or Q.");
                    break;
            }

            Position pos = rover.getPosition();
            System.out.println("Rover position: (" + pos.getX() + ", " + pos.getY() + ")");
        }

        scanner.close();
    }
}
