import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

// BOJ #3987 보이저 1호
public class Main {

    private static final char BLACK_HOLE = 'C';
    private static final char SLASH = '/';
    private static final char REVERSE_SLASH = '\\';
    private static final int INF = Integer.MAX_VALUE;
    private static int N, M;
    private static char[][] map;

    enum Direction {
        UP(0, 0, -1), RIGHT(1, 1, 0), DOWN(2, 0, 1), LEFT(3, -1, 0);

        private final int ordinal;
        private final int moveX;
        private final int moveY;

        Direction(int ordinal, int moveX, int moveY) {
            this.ordinal = ordinal;
            this.moveX = moveX;
            this.moveY = moveY;
        }

        public int getOrdinal() {
            return ordinal;
        }

        public int getMoveX() {
            return moveX;
        }

        public int getMoveY() {
            return moveY;
        }

        public Direction getReflectionDirection(boolean isSlash){
            if(isSlash){
                return switch (this) {
                    case UP -> RIGHT;
                    case RIGHT -> UP;
                    case DOWN -> LEFT;
                    case LEFT -> DOWN;
                };
            }
            return switch (this) {
                case UP -> LEFT;
                case RIGHT -> DOWN;
                case DOWN -> RIGHT;
                case LEFT -> UP;
            };
        }

        public char getChar() {
            return switch (this){
                case UP -> 'U';
                case RIGHT -> 'R';
                case DOWN -> 'D';
                case LEFT -> 'L';
            };
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String curLine = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = curLine.charAt(j);
            }
        }
        st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(main.solve(startX, startY));
    }

    private String solve(int startX, int startY) {
        boolean[][][] visited = new boolean[Direction.values().length][N][M];

        int maxTime = Integer.MIN_VALUE;
        Direction maxDir = null;

        for (Direction dir : Direction.values()) {
            int totalTime = move(startX, startY, dir, visited, 0);
            if(totalTime > maxTime){
                maxTime = totalTime;
                maxDir = dir;
            }
        }

        return new StringBuilder()
                .append(maxDir.getChar())
                .append("\n")
                .append(maxTime == INF ? "Voyager" : maxTime)
                .toString();
    }

    private int move(int x, int y, Direction dir, boolean[][][] visited, int curTime) {

        if (!isInBound(x, y) || map[y][x] == BLACK_HOLE) {
            return curTime;
        }

        if(visited[dir.getOrdinal()][y][x]) {
            return INF;
        }

        visited[dir.getOrdinal()][y][x] = true;

        Direction nextDirection = getNextDirection(x, y, dir);

        int ny = y + nextDirection.moveY;
        int nx = x + nextDirection.moveX;

        int totalTime = move(nx, ny, nextDirection, visited, curTime + 1);

        visited[dir.getOrdinal()][y][x] = false;

        return totalTime;
    }

    private boolean isInBound(int x, int y) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    private Direction getNextDirection(int x, int y, Direction curDir){
        if(map[y][x] == SLASH){
            return curDir.getReflectionDirection(true);
        }
        if(map[y][x] == REVERSE_SLASH){
            return curDir.getReflectionDirection(false);
        }

        return curDir;
    }
}
