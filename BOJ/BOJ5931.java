import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #5931 Cow Beauty Pageant
class Point{
    int y,x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Main {

    static int N, M;
    static int[][] map;
    static Queue<Point> Q = new LinkedList<>();
    static boolean[][] visited2;

    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }

    static void BFS1(Point p, int num){
        Queue<Point> curQ = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        curQ.add(p);
        visited[p.y][p.x] = true;
        map[p.y][p.x] = num;


        while(!curQ.isEmpty()){
            Point cur = curQ.poll();
            boolean flag = false;
            for(int i = 0 ; i < 4 ; i ++){
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if(!OOB(ny, nx) && map[ny][nx] == -1 && !visited[ny][nx]){
                    curQ.offer(new Point(ny, nx));
                    visited[ny][nx] = true;
                    map[ny][nx] = num;
                }
                if(num == 1 && !OOB(ny, nx) && map[ny][nx] == 0 && !flag){
                    flag = true;
                    visited2[cur.y][cur.x] = true;
                    Q.offer(cur);
                }
            }
        }
    }

    static int BFS2(int num){
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                Point cur = Q.poll();
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = cur.y + dy[j];
                    int nx = cur.x + dx[j];
                    if(!OOB(ny, nx) && !visited2[ny][nx] && map[ny][nx] != num){
                        if(map[ny][nx] == 0) {
                            Q.offer(new Point(ny, nx));
                            visited2[ny][nx] = true;
                        }
                        else {
                            return L;
                        }
                    }
                }
            }
            L ++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited2 = new boolean[N][M];
        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < M ; j ++){
                char cur = input.charAt(j);
                if(cur == 'X'){
                    map[i][j] = -1;
                }
            }
        }

        int num = 1;

        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] == -1){
                    BFS1(new Point(i, j), num);
                    num ++;
                }
            }
        }

        System.out.println(BFS2(1));

    }
}
