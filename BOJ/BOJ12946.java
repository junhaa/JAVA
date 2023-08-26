import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] dy = { -1, -1, 0, 1, 1, 0}, dx = { 0, 1, 1, 0, -1, -1};
    static char[][] map;
    static int[][] paint;
    static int min = 0, N;

    static boolean OOB(int y, int x){
        if(y >= 0 && y < N && x >= 0 && x < N) return false;
        return true;
    }
    static void DFS(int y, int x, int curColor){
        paint[y][x] = curColor;
        for(int i = 0 ; i < 6 ; i ++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(!OOB(ny, nx) && map[ny][nx] == 'X'){
                if(paint[ny][nx] == -1) {
                    min = Math.max(min, 2);
                    DFS(ny, nx, 1 - curColor);
                }
                else if(paint[ny][nx] == curColor){
                    System.out.println(3);
                    System.exit(0);
                }
            }

        }
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paint = new int[N][N];
        map = new char[N][N];
        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            Arrays.fill(paint[i], -1);
            for(int j = 0 ; j < N ; j ++){
                map[i][j] = input.charAt(j);
            }
        }
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < N ; j ++){
                if(map[i][j] == 'X' && paint[i][j] == -1){
                    min = Math.max(min, 1);
                    paint[i][j] = 0;
                    DFS(i, j, 0);
                }
            }
        }
        System.out.println(min);
    }
}
