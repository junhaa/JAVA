import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// BOJ #14461 소가 길을 건너는 이유 7
class Point implements Comparable<Point> {
    int y, x, move;
    long cost;
    public Point(int y, int x, long cost, int move) {
        this.move = move;
        this.y = y;
        this.x = x;
        this.cost = cost;
    }
    @Override
    public int compareTo(Point o){
        if(this.cost < o.cost) return -1;
        else if(this.cost > o.cost) return 1;
        else return 0;
    }
}
public class Main {
    static int N, T;
    static int[][] time;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return false;
        return true;
    }
    static long getMin(){
        PriorityQueue<Point> pQ = new PriorityQueue<>();
        pQ.offer(new Point(0, 0, 0, 0));
        long[][][] min = new long[3][N][N];
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < 3 ; j ++) {
                Arrays.fill(min[j][i], Long.MAX_VALUE);
            }
        }

        min[0][0][0] = 0;

        while(!pQ.isEmpty()){
            Point tmp = pQ.poll();
            if(min[tmp.move][tmp.y][tmp.x] < tmp.cost) continue;
            if(tmp.y == N - 1 && tmp.x == N - 1) continue;
            for(int i = 0 ; i < 4 ; i ++){
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                if(OOB(ny, nx)) continue;
                long ncost = tmp.cost + T;
                int nmove = (tmp.move + 1) % 3;
                if(nmove == 0){
                    ncost += time[ny][nx];
                }
                if(min[nmove][ny][nx] > ncost){
                    min[nmove][ny][nx] = ncost;
                    pQ.offer(new Point(ny, nx, ncost, nmove));
                }
            }
        }
        long ans = Long.MAX_VALUE;
        for(int i = 0 ; i < 3 ; i ++){
            ans = Math.min(ans, min[i][N - 1][N - 1]);
        }
        return ans;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        time = new int[N][N];
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j ++){
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(getMin());
    }
}
