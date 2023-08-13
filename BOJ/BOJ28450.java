import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #28450 컨벤 데드가 하고싶어요
class Point implements Comparable<Point>{
    int y, x;
    long num;
    public Point(int y, int x, long num){
        this.y = y;
        this.x = x;
        this.num = num;
    }
    @Override
    public int compareTo(Point o){
        if(this.num - o.num > 0) return 1;
        else if(this.num == o.num) return 0;
        else return -1;
    }
}
public class Main {
    static int[][] map;
    static int N, M;
    static long[][] visited;
    static int[] dx = { 1, 0 }, dy = { 0, 1 };

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        else return true;
    }
    static long BFS(){
        PriorityQueue<Point> pQ = new PriorityQueue<>();
        pQ.offer(new Point(0, 0, map[0][0]));
        visited[0][0] = map[0][0];
        while(!pQ.isEmpty()){
            Point tmp = pQ.poll();
            if(visited[tmp.y][tmp.x] > tmp.num) continue;
            if(tmp.y == N - 1 && tmp.x == M - 1) return tmp.num;
            for(int i = 0 ; i < 2 ; i ++){
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                if(!OOB(ny, nx)){
                    long nc = tmp.num + map[ny][nx];
                    if(nc < visited[ny][nx]) {
                        visited[ny][nx] = nc;
                        pQ.offer(new Point(ny, nx, nc));
                    }
                }
            }
        }
        return Long.MAX_VALUE;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new long[N][M];
        for(int i = 0 ; i < N ; i ++){
            Arrays.fill(visited[i], Long.MAX_VALUE);
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        long cost = BFS();
        if(cost <= Long.parseLong(br.readLine())){
            System.out.println("YES\n" + cost);
        }
        else {
            System.out.println("NO");
        }
    }
}
