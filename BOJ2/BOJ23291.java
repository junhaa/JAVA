import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #23291 어항 정리

/**
 * 1. 가장 적은 어항에 물고기를 한 마리 추가 (가장 적은 어항이 여러개라면, 각각 한 마리씩 추가)
 * 2. 어항 말기 (바닥에 있는 어항이 있어야 함)
 * 3. 어항에 있는 물고기의 수를 조절 (인접한 어항의 차 / 5), 모든 인접한 칸에 대해서 동시에 발생
 * 4. 어항을 일렬로 두기 (가장 왼쪽에 있는 어항부터, 아래에 있는 어항부터 순서대로)
 * 5. 공중 부양 작업 (가운데를 중심으로 왼쪽 N/2개를 시계 방향으로 180도 회전)
 * 6. 다시 일렬로 두기 (4와 동일)
 * 7. 물고기가 가장 많이 들어있는 어항과 가장 적게 들어있는 어항의 물고기 수의 차이가 K 이하가 되면 중단
 */

enum Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1),
    ;

    private final int dy;
    private final int dx;

    Direction(int dy, int dx) {
        this.dy = dy;
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public boolean isVertical() {
        return switch (this) {
            case UP, DOWN -> true;
            case LEFT, RIGHT -> false;
        };
    }

    public Direction rotateClockwise90() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }
}

public class Main {

    private static final int EMPTY = 0;
    private static final int ROLL_ARR_R = 10;
    private static final int ROLL_ARR_C = 20;

    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(new Main().solve(arr));
    }

    private int solve(int[] arr) {

        int count = 0;

        if (isMaxDissLessThan(K, arr)) {
            return count;
        }

        while (true) {
            arr = addOneFish(arr);

            int[][] rollArr = rollFishArr(arr);
            int[][] adjustRollArr = adjustFishSize(rollArr);
            arr = toArr(adjustRollArr);

            int[][] foldArr = foldArr(arr);
            int[][] adjustFoldArr = adjustFishSize(foldArr);
            arr = toArr(adjustFoldArr);

            count++;

            if (isMaxDissLessThan(K, arr)) {
                return count;
            }
        }
    }

    private int[] addOneFish(int[] arr) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] == min) {
                arr[i]++;
            }
        }

        return arr;
    }

    private int[][] rollFishArr(int[] arr) {
        int[][] rollArr = new int[ROLL_ARR_R][ROLL_ARR_C];

        int rollC = (int) Math.sqrt(N);
        int rollR = (rollC) * (rollC + 1) <= N ? rollC + 1 : rollC;

        for (int i = 0; i < N - (rollR * rollC); i++) {
            rollArr[ROLL_ARR_R - 1][rollC + i] = arr[rollR * rollC + i];
        }

        int curIdx = rollR * rollC - 1; // 0-based
        int curRLength = rollR - 1;
        int curCLength = rollC;
        Direction curDir = Direction.LEFT;
        int curR = ROLL_ARR_R - 1;
        int curC = rollC;

        while (curIdx >= 0) {
            int curCount = curDir.isVertical() ? curRLength : curCLength;
            for (int i = 0; i < curCount; i++) {
                curR = curR + curDir.getDy();
                curC = curC + curDir.getDx();
                rollArr[curR][curC] = arr[curIdx];
                curIdx--;
            }
            if (curDir.isVertical()) {
                curRLength--;
            }
            if (!curDir.isVertical()) {
                curCLength--;
            }

            curDir = curDir.rotateClockwise90();
        }

        return rollArr;
    }

    private int[][] adjustFishSize(int[][] rollArr) {
        int maxR = rollArr.length;
        int maxC = rollArr[0].length;
        int[][] adjustArr = new int[maxR][maxC];

        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (rollArr[i][j] == EMPTY) {
                    continue;
                }
                for (Direction dir : Direction.values()) {
                    int ny = i + dir.getDy();
                    int nx = j + dir.getDx();
                    if (!isInBoundArr(ny, nx, maxR, maxC) || rollArr[ny][nx] == EMPTY
                            || rollArr[ny][nx] >= rollArr[i][j]) {
                        continue;
                    }

                    int diff = (rollArr[i][j] - rollArr[ny][nx]) / 5;
                    adjustArr[ny][nx] += diff;
                    adjustArr[i][j] -= diff;
                }
            }
        }

        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                rollArr[i][j] += adjustArr[i][j];
            }
        }

        return rollArr;
    }

    private int[] toArr(int[][] from) {
        int[] arr = new int[N];
        int curIdx = 0;
        for (int i = 0; i < from[0].length; i++) {
            for (int j = from.length - 1; j >= 0; j--) {
                if (from[j][i] == EMPTY) {
                    continue;
                }
                arr[curIdx++] = from[j][i];
            }
        }

        if (curIdx != N) {
            throw new IllegalStateException();
        }

        return arr;
    }

    private int[][] foldArr(int[] arr) {
        int maxRLength = N / 4;

        int[][] foldArr = new int[4][N / 4];

        for (int i = 0; i < maxRLength; i++) {
            foldArr[0][i] = arr[N / 4 * 3 - 1 - i];
            foldArr[1][i] = arr[N / 4 * 1 + i];
            foldArr[2][i] = arr[N / 4 * 1 - 1 - i];
            foldArr[3][i] = arr[N / 4 * 3 + i];
        }

        return foldArr;
    }

    private boolean isMaxDissLessThan(int k, int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        return max - min <= k;
    }

    private boolean isInBoundArr(int r, int c, int maxR, int maxC) {
        return r >= 0 && c >= 0 && r < maxR && c < maxC;
    }
}
