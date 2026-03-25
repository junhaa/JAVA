

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// BOJ #21610 마법사 상어와 비바라기

/**
 * 1. 모든 구름이 일정한 방향으로 이동
 * 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
 * 3. 구름이 모두 사라짐
 * 4. 2에서 물이 증가한 칸에 대해서 물복사버그 마법 시전 (대각선 비교 후 물이 있으면 1증가, 범위 주의)
 * 5. 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름이 생김, 단 3에서 구름이 사라진 칸이 아니어야 함
 */

enum Direction {
    LEFT(0, -1),
    LEFT_UP(-1, -1),
    UP(-1, 0),
    RIGHT_UP(-1, 1),
    RIGHT(0, 1),
    RIGHT_DOWN(1, 1),
    DOWN(1, 0),
    LEFT_DOWN(1, -1),
    ;

    private final int dy;
    private final int dx;

    Direction(int dy, int dx) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction valueOf(int index) {
        return Direction.values()[index];
    }

    public static List<Direction> getDiagonals() {
        return List.of(LEFT_UP, RIGHT_UP, RIGHT_DOWN, LEFT_DOWN);
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }
}

class Movement {
    Direction direction;
    int count;

    public Movement(Direction direction, int count) {
        this.direction = direction;
        this.count = count;
    }

    public static List<Movement> getDiagonalMovements() {
        return Direction.getDiagonals().stream()
                .map(direction -> new Movement(direction, 1))
                .collect(Collectors.toList());
    }

    public Point move(Point point) {
        return new Point(point.x + (direction.getDx() * this.count), point.y + (direction.getDy() * this.count));
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isInBound(int boundX, int boundY) {
        return x >= 0 && x < boundX && y >= 0 && y < boundY;
    }

    public Point wrapWithinBounds(int boundX, int boundY) {
        int wrapX = this.x;
        int wrapY = this.y;
        if (wrapX < 0) {
            wrapX += boundX;
        }
        if (wrapX >= boundX) {
            wrapX -= boundX;
        }
        if (wrapY < 0) {
            wrapY += boundY;
        }
        if (wrapY >= boundY) {
            wrapY -= boundY;
        }

        return new Point(wrapX, wrapY);
    }
}

public class Main {

    private static int N;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Movement> movements = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int count = Integer.parseInt(st.nextToken());
            movements.add(new Movement(Direction.valueOf(index), count % N));
        }

        System.out.println(new Main().solve(A, movements));
    }

    private int solve(int[][] a, List<Movement> movements) {
        boolean[][] hasCloud = new boolean[N][N];
        hasCloud[N - 1][0] = true;
        hasCloud[N - 2][0] = true;
        hasCloud[N - 1][1] = true;
        hasCloud[N - 2][1] = true;

        for (int i = 0; i < M; i++) {
            hasCloud = moveClouds(hasCloud, movements.get(i));
            rain(a, hasCloud);
            waterDuplicateBug(a, hasCloud);
            hasCloud = makeClouds(a, hasCloud);
        }

        return calcWaterSum(a);
    }

    private boolean[][] moveClouds(boolean[][] hasCloud, Movement movement) {
        boolean[][] moveClouds = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!hasCloud[i][j]) {
                    continue;
                }
                Point cur = new Point(j, i);
                cur = movement.move(cur).wrapWithinBounds(N, N);
                moveClouds[cur.y][cur.x] = true;
            }
        }

        return moveClouds;
    }

    private void rain(int[][] a, boolean[][] hasCloud) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (hasCloud[i][j]) {
                    a[i][j]++;
                }
            }
        }
    }

    private void waterDuplicateBug(int[][] a, boolean[][] hasCloud) {
        List<Movement> diagonalMovements = Movement.getDiagonalMovements();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!hasCloud[i][j]) {
                    continue;
                }
                Point cur = new Point(j, i);
                for (Movement movement : diagonalMovements) {
                    Point diagonalPoint = movement.move(cur);
                    if (!diagonalPoint.isInBound(N, N)) {
                        continue;
                    }
                    if (a[diagonalPoint.y][diagonalPoint.x] > 0) {
                        a[cur.y][cur.x]++;
                    }
                }
            }
        }
    }

    private boolean[][] makeClouds(int[][] a, boolean[][] hasCloud) {
        boolean[][] canMakeClouds = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] >= 2 && !hasCloud[i][j]) {
                    canMakeClouds[i][j] = true;
                    a[i][j] -= 2;
                }
            }
        }

        return canMakeClouds;
    }

    private int calcWaterSum(int[][] a) {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += a[i][j];
            }
        }

        return sum;
    }
}
