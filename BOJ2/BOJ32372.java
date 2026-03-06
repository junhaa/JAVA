import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #32372 마법의 나침반
public class Main {

    private static int MIN_DEFAULT_VALUE = Integer.MIN_VALUE;
    private static int MAX_DEFAULT_VALUE = Integer.MAX_VALUE;
    private static int minX = MIN_DEFAULT_VALUE;
    private static int minY = MIN_DEFAULT_VALUE;
    private static int maxX = MAX_DEFAULT_VALUE;
    private static int maxY = MAX_DEFAULT_VALUE;



    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken())
                    - 1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(main.solve(N, map));
    }

    private String solve(int N, int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                updateIndexRange(j, map[i][j], true);
                updateIndexRange(i, map[i][j], false);
            }
        }

        return String.format("%d %d", getResultValue(minY, maxY) + 1, getResultValue(minX, maxX) + 1);
    }

    private void updateIndexRange(int index, int dir, boolean isXDir) {
        if (isXDir) {
            switch (dir) {
                case 1:
                case 5:
                    minX = index;
                    maxX = index;
                    return;

                case 2:
                case 3:
                case 4:
                    minX = Math.max(minX, index + 1);
                    return;

                case 6:
                case 7:
                case 8:
                    maxX = Math.min(maxX, index - 1);
                    return;

                default:
                    throw new IllegalArgumentException();
            }
        }

        switch (dir) {
            case 3:
            case 7:
                minY = index;
                maxY = index;
                return;

            case 4:
            case 5:
            case 6:
                minY = Math.max(minY, index + 1);
                return;

            case 1:
            case 2:
            case 8:
                maxY = Math.min(maxY, index - 1);
                return;

            default:
                throw new IllegalArgumentException();
        }
    }

    private int getResultValue(int minValue, int maxValue){
        if(minValue == maxValue) {
            return minValue;
        }
        if(minValue == MIN_DEFAULT_VALUE) {
            return maxValue;
        }
        if(maxValue == MAX_DEFAULT_VALUE) {
            return minValue;
        }
        throw new IllegalStateException();
    }
}
