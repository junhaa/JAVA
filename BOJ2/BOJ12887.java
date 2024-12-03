import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// BOJ #12887 - 경로 게임
class Point{
    int y, x, move;
    public Point(int y, int x, int move){
        this.y = y;
        this.x = x;
        this.move = move;
    }
}
public class Main {
    static int M, WHITE;
    static int[][] visited;
    static boolean[][] isWhite;
    static int[] dy = { 0, -1, 1};
    static int[] dx = { 1, 0, 0};
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        visited = new int[2][M];
        isWhite = new boolean[2][M];

        int whiteCnt = 0;
        for(int i = 0 ; i < 2 ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < M ; j ++){
                if(input.charAt(j) == '.'){
                    whiteCnt ++;
                    isWhite[i][j] = true;
                }
            }
        }

        int len = main.bfs();
        System.out.println(whiteCnt - len);
    }

    public int bfs(){
        Queue<Point> q = new LinkedList<>();
        for(int i = 0 ; i < 2 ; i ++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
            if(isWhite[i][0]){
                q.offer(new Point(i, 0, 0));
                visited[i][0] = 1;
            }
        }

        int L = 1;
        while(!q.isEmpty()){
            int len = q.size();
            L ++;
            for(int i = 0 ; i < len ; i ++){
                Point cur = q.poll();
                if(cur.move > visited[cur.y][cur.x]) continue;
                for(int j = 0 ; j < 3 ; j ++){
                    int ny = cur.y + dy[j];
                    int nx = cur.x + dx[j];
                    if(checkBound(ny, nx) && visited[ny][nx] == Integer.MAX_VALUE && isWhite[ny][nx]){
                        q.offer(new Point(ny, nx, L));
                        visited[ny][nx] = L;
                    }
                }
            }
        }
        int min = Math.min(visited[0][M - 1], visited[1][M - 1]);
        if(min == Integer.MAX_VALUE) return -1;
        return min;
    }

    public boolean checkBound(int y, int x){
        return y >= 0 && y < 2 && x >= 0 && x < M;
    }

}
