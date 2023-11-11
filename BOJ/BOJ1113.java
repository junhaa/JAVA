import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ #1113 수영장 만들기
class Point implements Comparable<Point> {
    int y, x, height;

    public Point(int y, int x, int height) {
        this.y = y;
        this.x = x;
        this.height = height;
    }

    @Override
    public int compareTo(Point o) {
        return this.height - o.height;
    }
}

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int answer = 0;
    static boolean[][] visited;
    static PriorityQueue<Point> pQ = new PriorityQueue<>();

    static boolean OOB(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < M) return false;
        else return true;
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        for (int i = 0; i < M; i++) {
            visited[0][i] = true;
            visited[N - 1][i] = true;
            pQ.offer(new Point(0, i, map[0][i]));
            pQ.offer(new Point(N - 1, i, map[N - 1][i]));
        }

        for (int i = 1; i < N - 1; i++) {
            visited[i][0] = true;
            visited[i][M - 1] = true;
            pQ.offer(new Point(i, 0, map[i][0]));
            pQ.offer(new Point(i, M - 1, map[i][M - 1]));
        }

        int answer = 0;

        while (!pQ.isEmpty()) {
            Point tmp = pQ.poll();
            for(int i = 0 ; i < 4 ; i ++) {
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                if(T.OOB(ny, nx)) continue;
                if(visited[ny][nx]) continue;
                int dif = tmp.height - map[ny][nx];
                if(dif > 0) answer += dif;
                visited[ny][nx] = true;
                pQ.offer(new Point(ny, nx, Math.max(map[ny][nx], tmp.height)));
            }
        }

        System.out.println(answer);
    }
}
