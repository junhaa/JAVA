import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #16932 모양 만들기
public class Main {
    static int N, M;
    static int[][] map;
    static int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };

    static boolean[][] visited;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < M) return false;
        return true;
    }
    static int DFS(int y, int x, int num){
        map[y][x] = num;
        int sum = 0;
        for(int i = 0 ; i < 4; i ++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!OOB(ny, nx) && map[ny][nx] == -1){
                sum += DFS(ny, nx, num);
            }
        }
        return sum + 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = Integer.parseInt(st.nextToken()) - 2;
            }
        }
        int num = 0;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] == -1){
                    list.add(DFS(i, j, num++)); // 0-based
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                if(map[i][j] == -2){
                    int sum = 0;
                    HashSet<Integer> set = new HashSet<>();
                    for(int k = 0 ; k < 4 ; k ++){
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if(!OOB(ny, nx) && map[ny][nx] >= 0){
                            set.add(map[ny][nx]);
                        }
                    }
                    for(int idx : set){
                        sum += list.get(idx);
                    }
                    max = Math.max(sum + 1, max);
                }
            }
        }
        System.out.println(max);
    }
}
