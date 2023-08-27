import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int y, x, virus;
    public Point(int y, int x, int virus){
        this.y = y;
        this.x = x;
        this.virus = virus;
    }
}
public class Main {

    static int N, M;
    static int[][] map, visited;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }
    static void BFS(Point vi1, Point vi2){
        int L = 1;
        Queue<Point> Q = new LinkedList<>();
        visited[vi1.y][vi1.x] = 0;
        visited[vi2.y][vi2.x] = 0;
        Q.offer(vi1);
        Q.offer(vi2);
        while(!Q.isEmpty()){
            int len =Q.size();
            for(int i = 0 ; i < len ; i ++){
                Point tmp = Q.poll();
                if(map[tmp.y][tmp.x] == 3) continue;
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = tmp.y + dy[j];
                    int nx = tmp.x + dx[j];
                    if(!OOB(ny, nx) && map[ny][nx] != -1){
                        if(visited[ny][nx] == L && map[ny][nx] == 3 - tmp.virus){
                            map[ny][nx] = 3;
                            continue;
                        }
                        else if(visited[ny][nx] == -1){
                            map[ny][nx] = tmp.virus;
                            visited[ny][nx] = L;
                            Q.offer(new Point(ny, nx, tmp.virus));
                        }
                    }
                }
            }
            L ++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Point vi1 = null, vi2 = null;
        map = new int[N][M];
        visited = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(visited[i], -1);
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    vi1 = new Point(i, j, 1);
                }
                else if(map[i][j] == 2){
                    vi2 = new Point(i, j, 2);
                }
            }
        }
        BFS(vi1, vi2);
        int[] answer = new int[3];
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                int tmp = map[i][j];
                if(tmp >= 1){
                    answer[tmp - 1] ++;
                }
            }
        }
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}
