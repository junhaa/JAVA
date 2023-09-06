import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #17836 공주님을 구해라!
class Point{
    int y, x;
    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}


public class Main {
    static int N, M, T;
    static int[][] map;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static boolean[][] visited;
    static final int MAX = (int)1e9;
    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }
    static int BFS(Point end){
        visited = new boolean[N][M];
        Queue<Point> Q = new LinkedList<>();
        Q.offer(new Point(0, 0));
        visited[0][0] = true;
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                Point tmp = Q.poll();
                if(tmp.y == end.y && tmp.x == end.x) return L;
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = tmp.y + dy[j];
                    int nx = tmp.x + dx[j];
                    if(!OOB(ny, nx) && !visited[ny][nx] && map[ny][nx] != 1){
                        Q.offer(new Point(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
            L ++;
        }
        return MAX;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point g = null;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    map[i][j] = 0;
                    g = new Point(i, j);
                }
            }
        }
        int min = Math.min(BFS(g) + N - 1 - g.y + M - 1 - g.x, BFS(new Point(N - 1, M - 1)));
        if(min > T){
            System.out.println("Fail");
        }
        else{
            System.out.println(min);
        }
    }
}
