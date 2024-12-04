import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ#23352 - 방탈출
class Result{
    int len, num;
    public Result(int len, int num){
        this.len = len;
        this.num = num;
    }
}
class Point{
    int y, x;
    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }
}
public class Main {
    static private int N, M;
    static private int[][] map;
    static private int[] dy = {-1, 0, 1, 0}, dx = { 0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0 ; i < N ; i ++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxLen = Integer.MIN_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] != 0){
                    Result res = main.bfs(new Point(i, j));
                    if(maxLen < res.len){
                        maxLen = res.len;
                        maxNum = map[i][j] + res.num;
                    }
                    else if(maxLen == res.len && maxNum < res.num + map[i][j]){
                        maxNum = res.num + map[i][j];
                    }
                }
            }
        }
        System.out.println(maxNum);
    }

    public Result bfs(Point start){
        int[][] visited = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        visited[start.y][start.x] = 1;
        int L = 1;
        while(!q.isEmpty()){
            L ++;
            int len = q.size();
            for(int i = 0 ; i < len ; i ++){
                Point cur = q.poll();
                for(int j = 0 ; j < 4 ; j ++){
                    int ny = cur.y + dy[j];
                    int nx = cur.x + dx[j];
                    if(checkBound(ny, nx) && visited[ny][nx] > L && map[ny][nx] > 0){
                        visited[ny][nx] = L;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }

        Point max = getMaxLenPoint(visited);
        return new Result(visited[max.y][max.x], map[max.y][max.x]);

    }

    private Point getMaxLenPoint(int[][] visited){
        int max = Integer.MIN_VALUE;
        int maxy = -1;
        int maxx = -1;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(visited[i][j] != Integer.MAX_VALUE && max <= visited[i][j]){
                    if(max < visited[i][j]){
                        max = visited[i][j];
                        maxy = i;
                        maxx = j;
                    }
                    else {
                        if(map[maxy][maxx] < map[i][j]){
                            maxy = i;
                            maxx = j;
                        }
                    }
                }
            }
        }

        return new Point(maxy, maxx);
    }

    private boolean checkBound(int y, int x){
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}
