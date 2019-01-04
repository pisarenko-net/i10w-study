import java.util.HashSet;
import java.util.Set;

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class RobotRoomCleaner {
    private class Coord {
        int x, y;
        
        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public boolean equals(Object that) {
            return this.x == ((Coord)that).x && this.y == ((Coord)that).y;
        }
        
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    
    private enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }
    
    Set<Coord> visited;
    Direction direction;
    Robot robot;
    
    public void cleanRoom(Robot robot) {
        this.visited = new HashSet<Coord>();
        this.direction = Direction.UP;
        this.robot = robot;
        visit(new Coord(0, 0));
    }
    
    private void visit(Coord coord) {
        robot.clean();
        visited.add(coord);
        for (int i = 0; i < 4; i++) {
            Coord next = getNext(direction, coord);
            if (!visited.contains(next) && robot.move()) {
                visit(next);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            direction = turn(direction);
        }
    }
    
    private Coord getNext(Direction direction, Coord coord) {
        if (direction == Direction.UP) return new Coord(coord.x, coord.y + 1);
        else if (direction == Direction.DOWN) return new Coord(coord.x, coord.y - 1);
        else if (direction == Direction.RIGHT) return new Coord(coord.x + 1, coord.y);
        else if (direction == Direction.LEFT) return new Coord(coord.x - 1, coord.y);
        throw new IllegalStateException("invalid direction");
    }
    
    private Direction turn(Direction direction) {
        robot.turnRight();
        if (direction == Direction.UP) return Direction.RIGHT;
        else if (direction == Direction.RIGHT) return Direction.DOWN;
        else if (direction == Direction.DOWN) return Direction.LEFT;
        else return Direction.UP;
    }
}