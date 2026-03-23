import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #23289 온풍기 안녕!
enum Direction {
    UP(0, -1, 0),
    RIGHT(1, 0, 1),
    DOWN(2, 1, 0),
    LEFT(3, 0, -1);

    private final int index;
    private final int dy;
    private final int dx;

    Direction(int index, int dy, int dx) {
        this.index = index;
        this.dy = dy;
        this.dx = dx;
    }

    public static Direction valueOf(int index) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.index == index)
                .findFirst()
                .get();
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public Direction getOpposite() {
        return Direction.valueOf((this.index + 2) % 4);
    }
}

class Heater {
    private Direction direction;
    private Point point;

    public Heater(Direction direction, Point point) {
        this.direction = direction;
        this.point = point;
    }

    public Blow getStartBlow() {
        return new Blow(point.move(direction), direction, 5);
    }
}

class Blow {
    private Point point;
    private Direction direction;
    private int temperature;

    public Blow(Point point, Direction direction, int temperature) {
        this.point = point;
        this.direction = direction;
        this.temperature = temperature;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getTemperature() {
        return temperature;
    }
}

class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point move(Direction direction) {
        return new Point(this.x + direction.getDx(), this.y + direction.getDy());
    }
}

class Wall {
    private Point point;
    private Direction blockDirection;

    public Wall(Point point, Direction blockDirection) {
        this.point = point;
        this.blockDirection = blockDirection;
    }

    public boolean isBlocked(Point point, Direction direction) {
        return this.point.equals(point) && this.blockDirection == direction;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Point> detectPoints = new ArrayList<>();
        List<Heater> heaters = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < c; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (cur == 0) {
                    continue;
                }
                if (cur == 5) {
                    detectPoints.add(new Point(j, i));
                    continue;
                }

                int valueIdx = switch (cur) {
                    case 1 -> 1;
                    case 2 -> 3;
                    case 3 -> 0;
                    case 4 -> 2;
                    default -> throw new IllegalArgumentException();
                };
                heaters.add(new Heater(Direction.valueOf(valueIdx), new Point(j, i)));
            }
        }

        List<Wall> walls = new ArrayList<>();

        int w = Integer.parseInt(br.readLine());
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            Point curPoint = new Point(x, y);

            Direction direction = switch (t) {
                case 0 -> Direction.UP;
                case 1 -> Direction.RIGHT;
                default -> throw new IllegalArgumentException();
            };
            walls.add(new Wall(curPoint, direction));

            Point oppositePoint = curPoint.move(direction);
            if (main.isInBound(r, c, oppositePoint)) {
                walls.add(new Wall(oppositePoint, direction.getOpposite()));
            }
        }

        System.out.println(main.solve(r, c, k, detectPoints, heaters, walls));
    }

    private int solve(int r, int c, int k, List<Point> detectPoints, List<Heater> heaters, List<Wall> walls) {
        int[][] temperature = new int[r][c];

        int chocolate = 0;

        while (chocolate <= 100) {
            runAllHeaters(r, c, temperature, heaters, walls);
            adjustTemperature(r, c, temperature, walls);
            adjustBoundTemperature(r, c, temperature);
            chocolate++;
            if (isValidTemperaturePoints(temperature, detectPoints, k)) {
                return chocolate;
            }
        }

        return 101;
    }

    private void runAllHeaters(int r, int c, int[][] temperature, List<Heater> heaters, List<Wall> walls) {
        for (Heater heater : heaters) {
            int[][] curTemperature = blowHeater(r, c, heater, walls);

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    temperature[i][j] += curTemperature[i][j];
                }
            }
        }
    }

    private void adjustTemperature(int r, int c, int[][] temperature, List<Wall> walls) {
        int[][] adjustTemperature = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Point cur = new Point(j, i);
                for (Direction direction : Direction.values()) {
                    if (isBlockedByWall(walls, cur, direction)) {
                        continue;
                    }
                    Point other = cur.move(direction);
                    if (!isInBound(r, c, other)) {
                        continue;
                    }
                    if (temperature[other.getY()][other.getX()] >= temperature[cur.getY()][cur.getX()]) {
                        continue;
                    }
                    int diff = (temperature[cur.getY()][cur.getX()] - temperature[other.getY()][other.getX()]) / 4;
                    adjustTemperature[other.getY()][other.getX()] += diff;
                    adjustTemperature[cur.getY()][cur.getX()] -= diff;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                temperature[i][j] += adjustTemperature[i][j];
            }
        }
    }

    private void adjustBoundTemperature(int r, int c, int[][] temperature) {
        for (int i = 0; i < r; i++) {
            if (temperature[i][0] >= 1) {
                temperature[i][0]--;
            }
        }

        for (int i = 1; i < c; i++) {
            if (temperature[r - 1][i] >= 1) {
                temperature[r - 1][i]--;
            }
        }

        for (int i = r - 2; i >= 0; i--) {
            if (temperature[i][c - 1] >= 1) {
                temperature[i][c - 1]--;
            }
        }

        for (int i = c - 2; i >= 1; i--) {
            if (temperature[0][i] >= 1) {
                temperature[0][i]--;
            }
        }
    }

    private boolean isValidTemperaturePoints(int[][] temperature, List<Point> detectPoints, int K) {
        for (Point point : detectPoints) {
            if (temperature[point.getY()][point.getX()] < K) {
                return false;
            }
        }

        return true;
    }

    private int[][] blowHeater(int r, int c, Heater heater, List<Wall> walls) {
        int[][] curTemperature = new int[r][c];

        Queue<Blow> queue = new LinkedList<>();
        queue.offer(heater.getStartBlow());

        while (!queue.isEmpty()) {
            Blow cur = queue.poll();
            Point curPoint = cur.getPoint();
            Direction curDirection = cur.getDirection();
            int temperature = cur.getTemperature();

            if(curTemperature[curPoint.getY()][curPoint.getX()] != 0) continue;
            curTemperature[curPoint.getY()][curPoint.getX()] = temperature;

            if (temperature <= 1) {
                continue;
            }

            for (Direction direction : Direction.values()) {
                if (direction == curDirection.getOpposite()) {
                    continue;
                }

                if(isBlockedByWall(walls, curPoint, direction)) {
                   continue;
                }

                Point nextPoint = curPoint.move(direction);

                if(direction != curDirection) {
                    if(isBlockedByWall(walls, nextPoint, curDirection)) {
                        continue;
                    }
                    nextPoint = nextPoint.move(curDirection);
                }

                if (!isInBound(r, c, nextPoint)) {
                    continue;
                }

                queue.offer(new Blow(nextPoint, curDirection, temperature - 1));
            }
        }

        return curTemperature;
    }

    private boolean isInBound(int r, int c, Point curPoint) {
        return curPoint.getY() >= 0 && curPoint.getY() < r && curPoint.getX() >= 0 && curPoint.getX() < c;
    }

    private boolean isBlockedByWall(List<Wall> walls, Point curPoint, Direction direction) {
        return walls.stream()
                .filter(wall -> wall.isBlocked(curPoint, direction))
                .findFirst()
                .isPresent();
    }
}
