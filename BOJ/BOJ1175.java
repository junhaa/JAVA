import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #1175 배달
class Point{
    int y, x, lastDir, delivered;
    public Point(int y, int x, int lastDir, int delivered){
        this.y = y;
        this.x = x;
        this.lastDir = lastDir;
        this.delivered = delivered;
    }
}
public class Main {
    static char[][] map;
    static int N, M;
    static int[] dx = { 0, 1, 0, -1}, dy = { -1, 0, 1, 0 };
    static Point C1, C2;
    static boolean[][][][] visited;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        else return true;
    }
    static int BFS(Point start){
        Queue<Point> Q = new LinkedList<>();
        Q.offer(start);
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                Point tmp = Q.poll();
                if(tmp.delivered == 3) return L;
                for(int j = 0 ; j < 4 ; j ++){
                    if(tmp.lastDir == j) continue;
                    int ny = tmp.y + dy[j];
                    int nx = tmp.x + dx[j];
                    int tmpD = tmp.delivered;
                    if(!OOB(ny, nx) && map[ny][nx] != '#' && !visited[tmp.delivered][j][ny][nx]){
                        if(map[ny][nx] == 'C') {
                            if(C1.y == ny && C1.x == nx) { // C1
                                if(tmpD != 1) tmpD += 1;
                            }
                            else { // C2
                                if(tmpD != 2) tmpD += 2;
                            }
                        }
                        Q.offer(new Point(ny, nx, j, tmpD));
                        visited[tmp.delivered][j][ny][nx] = true;
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
        map = new char[N][M];
        visited = new boolean[4][4][N][M];
        Point start = null;
        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'S'){
                    start = new Point(i, j, -1, 0);
                }
                else if(map[i][j] == 'C'){
                    if(C1 == null){
                        C1 = new Point(i, j, -1, -1);
                    }
                    else{
                        C2 = new Point(i, j, -1, -1);
                    }
                }
            }
        }
        System.out.println(BFS(start));
    }
}
