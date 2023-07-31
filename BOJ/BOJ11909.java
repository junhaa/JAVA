import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// BOJ #11909 배열 탈출
class Point implements Comparable<Point>{
    int y, x, cost;
    public Point(int y, int x, int cost){
        this.y = y;
        this.x = x;
        this.cost= cost;
    }
    @Override
    public int compareTo(Point o){
        return this.cost - o.cost;
    }
}

public class Main {

    static int[][] map, visited;
    static int[] dx = { 1, 0 }, dy = { 0, 1 };
    static int n;
    static boolean OOB(int y, int x){
        if(y >= 0 && y < n && x >= 0 && x < n) return false;
        else return true;
    }
    static void dijkstra(){
        PriorityQueue<Point> pQ = new PriorityQueue<>();
        pQ.offer(new Point(0, 0, 0));
        visited[0][0] = 0;
        while(!pQ.isEmpty()){
            Point tmp = pQ.poll();
            if(tmp.y == n - 1 && tmp.x == n - 1) return;
            if(visited[tmp.y][tmp.x] < tmp.cost) continue;
            for(int i = 0 ; i < 2 ; i ++){
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                if(OOB(ny, nx)) continue;
                int nc = tmp.cost;
                if(map[tmp.y][tmp.x] <= map[ny][nx]) nc += map[ny][nx] - map[tmp.y][tmp.x] + 1;
                if(visited[ny][nx] > nc){
                    pQ.offer(new Point(ny, nx, nc));
                    visited[ny][nx] = nc;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[n][n];
        visited = new int[n][n];
        for(int i = 0 ; i < n ; i ++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < n ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dijkstra();
        System.out.println(visited[n - 1][n - 1]);
    }
}
