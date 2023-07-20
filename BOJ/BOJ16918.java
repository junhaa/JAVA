import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #16918 봄버맨
class Point{
    int y, x, time;
    public Point(int y, int x, int time) {
        this.y = y;
        this.x = x;
        this.time = time;
    }
}
public class Main {

    static int R, C, N;
    static Queue<Point> Q = new LinkedList<>();
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 } , dy = { -1, 0, 1, 0 };

    static StringBuilder printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < R ; i ++) {
            for(int j = 0 ; j < C ; j ++) {
                if(map[i][j] == -1) {
                    sb.append('.');
                }
                else sb.append('O');
            }
            sb.append("\n");
        }
        return sb;
    }

    static boolean OOB(int y, int x) {
        if(y >= 0 && y < R && x >= 0 && x < C) return false;
        else return true;
    }

    static void explode(int L) {
        while(!Q.isEmpty() && Q.peek().time + 3 <= L) {
            Point tmp = Q.poll();
            if(map[tmp.y][tmp.x] != tmp.time) continue;
            map[tmp.y][tmp.x] = -1;
            for(int i = 0 ; i < 4 ; i ++) {
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                if(OOB(ny, nx) || map[ny][nx] == tmp.time) continue;
                map[ny][nx] = -1;
            }
        }
    }

    static void fillBomb(int L) {
        for(int i = 0 ; i < R ; i ++) {
            for(int j = 0 ; j < C ; j ++) {
                if(map[i][j] == -1) {
                    map[i][j] = L;
                    Q.offer(new Point(i, j, L));
                }
            }
        }
    }


    static void BFS() {
        int L = 1;
        while(L < N) {
            if(L % 2 == 1) {
                fillBomb(L + 1);
            }
            else {
                explode(L + 1);
            }
            L ++;
        }
    }

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int i = 0 ; i < R ; i ++) {
            String input = br.readLine();
            for(int j = 0 ; j < C ; j ++) {
                if(input.charAt(j) == '.') {
                    map[i][j] = -1;
                }
                else {
                    map[i][j] = 0;
                    Q.offer(new Point(i, j, 0));
                }
            }
        }
        if(N != 0) T.BFS();
        System.out.println(T.printMap());
    }
}
