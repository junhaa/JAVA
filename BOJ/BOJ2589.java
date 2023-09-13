import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #2589 보물섬
class Point{
    int y, x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Main {
    static int N, M;
    static char[][] map;
    static int[][] time;
    static int[] dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 };


    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }
    static int BFS(int sy, int sx){
        boolean[][] visited = new boolean[N][M];
        visited[sy][sx] = true;
        Queue<Point> Q = new LinkedList<>();
        Q.offer(new Point(sy, sx));
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                Point tmp = Q.poll();
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = tmp.y + dy[j];
                    int nx = tmp.x + dx[j];
                    if(!OOB(ny, nx) && !visited[ny][nx] && map[ny][nx] == 'L'){
                        Q.offer(new Point(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
            L ++;
        }
        return L - 1;
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        time = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < M ; j ++){
                char tmp = input.charAt(j);
                map[i][j] = tmp;
            }
        }
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] == 'L'){
                    time[i][j] = BFS(i, j);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++) {
                max = Math.max(time[i][j], max);
            }
        }
        System.out.println(max);
    }
}
