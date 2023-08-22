import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ 4095 최대 정사각형
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] map = new int[N + 1][M + 1];
            if(N == 0 && M == 0) break;
            int answer = 0;
            for(int i = 1 ; i <= N ; i ++){
                st = new StringTokenizer(br.readLine());
                for(int j = 1 ; j <= M ; j ++){
                    int tmp = Integer.parseInt(st.nextToken());
                    if(tmp == 0) continue;
                    answer = Math.max(map[i][j] = Math.min(Math.min(map[i][j - 1], map[i - 1][j]), map[i - 1][j - 1]) + 1, answer);
                }
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
}
