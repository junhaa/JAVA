import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ #15730 수영장 사장님
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

    /*
    static int getMinDFS(int y, int x) {
        dis[y][x] = 1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!OOB(ny, nx) && dis[ny][nx] == -1) {
                if(map[ny][nx] < map[y][x]){
                    dis[y][x] = -1;
                    return Integer.MAX_VALUE;
                }
                min = Math.min(map[ny][nx], min);
            }
        }
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!OOB(ny, nx) && dis[ny][nx] == 0) {
                if (map[ny][nx] >= min) {
                    dis[ny][nx] = -1;
                    continue;
                } else min = Math.min(getMinDFS(ny, nx), min);
            }
        }

        return min;
    }

    static int fillDFS(int y, int x, int height) {
        dis[y][x] = 2;
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!OOB(ny, nx) && dis[ny][nx] == 1) {
                sum += fillDFS(ny, nx, height);
            }
        }
        sum += height - map[y][x];
        return sum;
    }

    
    static int DFS(int y, int x, int lastMin) {
        visited[y][x] = true;
        int min = lastMin;
        int nmin = min;
        for(int i = 0 ; i < 4 ; i ++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(dis[ny][nx] == -1) {
                min = Math.min(min, map[ny][nx]);
            }
        }
         for(int i = 0 ; i < 4 ; i ++){
             int ny = y + dy[i];
             int nx = x + dx[i];
             if(dis[ny][nx] == 0){
                 if(map[ny][nx] >= min) dis[ny][nx] = -1;
                 else if(!visited[ny][nx]) nmin = Math.min(nmin, DFS(ny, nx, min));
             }
         }

        min = Math.min(min, nmin);
        if(min <= map[y][x]) dis[y][x] = -1;
        else answer += min - map[y][x];
        return min;
    }


    static void bound_BFS(int y, int x) { // 밖으로 물이 빠져나갈 수 있는 칸 표시
        Queue<Point> Q = new LinkedList<Point>();
        boolean[][] visited = new boolean[N][M];
        visited[y][x] = true;
        Q.offer(new Point(y, x, map[y][x]));
        while (!Q.isEmpty()) {
            Point tmp = Q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = tmp.y + dy[i];
                int nx = tmp.x + dx[i];
                if (!OOB(ny, nx) && !visited[ny][nx] && dis[ny][nx] == 0 && map[ny][nx] >= tmp.height) {
                    dis[ny][nx] = -1;
                    Q.offer(new Point(ny, nx, map[ny][nx]));
                }
            }
        }
    }
	*/

    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
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
        /*
        for(int i = 0 ; i < N ; i ++) {
            for(int j = 0 ; j < M ; j ++) {
                if(dis[i][j] != -1 && !visited[i][j]) T.DFS(i, j, Integer.MAX_VALUE);
            }
        }
        */

    }
}
