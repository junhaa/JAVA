import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #1051 숫자 정사각형
public class Main {
    static int[][] map;
    static boolean isSame(int y, int x, int k){
        int cur = map[y][x];
        int ny = y + k;
        int nx = x + k;
        if((map[ny][x] == map[y][nx]) && map[ny][nx] == cur && map[ny][x] == cur){
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < M ; j ++){
                map[i][j] = input.charAt(j) - '0';
            }
        }
        int answer = 1;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++){
                int len = Math.min(N - i , M - j);
                int cur = map[i][j];
                for(int k = 1 ; k < len ; k ++){
                    if(isSame(i, j, k)){
                        answer = Math.max(answer, k + 1);
                    }
                }
            }
        }
        System.out.println(answer * answer);
    }
}
