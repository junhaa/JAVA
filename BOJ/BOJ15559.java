import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #15559 내 선물을 받아줘
public class Main {
    static int N, M, gnum = 0;
    static int[][] parent, map;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }

    static int DFS(int y, int x){
        parent[y][x] = -1;
        int dir = map[y][x];
        int ny = y + dy[dir];
        int nx = x + dx[dir];
        if(OOB(ny, nx) || parent[ny][nx] == -1){
            gnum ++;
            parent[y][x] = gnum;
            return gnum;
        }
        if(parent[ny][nx] != 0){
            parent[y][x] = parent[ny][nx];
            return parent[y][x];
        }
        else{
            parent[y][x] = DFS(ny, nx);
            return parent[y][x];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        parent = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < M ; j ++) {
                char dir = input.charAt(j);
                int dirtoint = -1;
                if(dir == 'N'){
                    dirtoint = 0;
                }
                else if(dir == 'E'){
                    dirtoint = 1;
                }
                else if(dir == 'S'){
                    dirtoint = 2;
                }
                else dirtoint = 3;
                map[i][j] = dirtoint;
            }
        }
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(parent[i][j] == 0){
                   DFS(i, j);
                }
            }
        }
        System.out.println(gnum);
    }
}
