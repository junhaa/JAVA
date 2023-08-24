import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #11967 불 켜기 
class Point{
    int y, x;
    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}
public class Main {
    static ArrayList<Point>[] edge;
    static int N;
    static int[] dx = { 0, 1, 0, -1 }, dy = { -1, 0, 1, 0 };
    static boolean[][] light, visited;
    static int convidx(int y, int x){
        return y * N + x;
    }

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return false;
        else return true;
    }

    static void BFS(){
        Queue<Point> Q = new LinkedList<>();
        light[0][0] = true;
        Q.offer(new Point(0, 0));
        visited[0][0] = true;
        while(!Q.isEmpty()){
            int len = Q.size();
            boolean flag = false;
            for(int j = 0 ; j < len ; j ++) {
                Point tmp = Q.poll();
                if (!light[tmp.y][tmp.x]) {
                    Q.offer(tmp);
                    continue;
                }
                for(Point x : edge[convidx(tmp.y, tmp.x)]){
                    light[x.y][x.x] = true;
                }
                flag = true;
                for (int i = 0; i < 4; i++) {
                    int ny = tmp.y + dy[i];
                    int nx = tmp.x + dx[i];
                    if (!OOB(ny, nx) && !visited[ny][nx]) {
                        Q.offer(new Point(ny, nx));
                        visited[ny][nx] = true;
                    }
                }
            }
            if(!flag) break;
        }

    }


    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        edge = new ArrayList[N * N];
        light = new boolean[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N * N ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = (Integer.parseInt(st.nextToken()) - 1) * N + (Integer.parseInt(st.nextToken()) - 1);
            int ey = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            edge[start].add(new Point(ey, ex));
        }

        BFS();
        int answer = 0;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                if(light[i][j]) answer ++;
            }
        }
        System.out.println(answer);
    }
}
