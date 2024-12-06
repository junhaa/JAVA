import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #27211 - 도넛 행성
class Point{
    int x, y;

    public Point(int y, int x) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static int[] dy = { -1, 0, 1, 0}, dx = { 0, 1, 0, - 1};
    static boolean[][] visited, isForest;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        isForest = new boolean[N][M];

        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                if(Integer.parseInt(st.nextToken()) == 1){
                    isForest[i][j] = true;
                }
            }
        }

        int L = 0;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(!visited[i][j] && !isForest[i][j]){
                    L ++;
                    main.bfs(new Point(i, j));
                }
            }
        }

        System.out.println(L);
    }

    public void bfs(Point start){
        Queue<Point> q = new LinkedList();
        q.offer(start);
        visited[start.y][start.x] = true;

        while(!q.isEmpty()){
            Point cur = q.poll();
            for(int i = 0 ; i < 4 ; i ++){
                int ny = getY(cur.y + dy[i]);
                int nx = getX(cur.x + dx[i]);
                if(!visited[ny][nx] && !isForest[ny][nx]){
                    q.offer(new Point(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }
    }

    public int getY(int ny){
        if(ny >= N){
            return ny % N;
        }
        else if(ny < 0){
            return ny + N;
        }
        else return ny;
    }

    public int getX(int nx){
        if(nx >= M){
            return nx % M;
        }
        else if(nx <0){
            return nx + M;
        }
        else return nx;
    }
}


