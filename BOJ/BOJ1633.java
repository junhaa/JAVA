import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #1633 최고의 팀 만들기 
public class Main {
    static int[][][] dp;
    static int[][] stat = new int[2][1000];
    static int cnt = 0;

    static int getSum(int idx, int black, int white){
        if(dp[idx][black][white] != 0){
            return dp[idx][black][white];
        }
        if(black == 15 && white == 15){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        if(cnt - idx > 30 - black - white) max = Math.max(max, getSum(idx + 1, black, white));
        if(black < 15) max = Math.max(max, stat[0][idx] + getSum(idx + 1, black + 1, white));
        if(white < 15) max = Math.max(max, stat[1][idx] + getSum(idx + 1, black, white + 1));

        return dp[idx][black][white] = max;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String input;
        while((input = br.readLine()) != null){
            st = new StringTokenizer(input);
            stat[0][cnt] = Integer.parseInt(st.nextToken());
            stat[1][cnt] = Integer.parseInt(st.nextToken());
            cnt ++;
        }
        dp = new int[cnt + 1][16][16];
        int answer = getSum(0, 0, 0);
        System.out.println(answer);
    }

}
