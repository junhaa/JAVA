import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #2216 문자열과 점수
public class Main {
    static int[][] dp;
    static int A, B, C;
    static String x, y;
    static final int MIN = -1_000_000_000;

    static int getMax(int xlen, int ylen){
        if(xlen == x.length()){
            return (y.length() - ylen) * B;
        }
        else if(ylen == y.length()){
            return (x.length() - xlen) * B;
        }
        if(dp[xlen][ylen] != MIN) return dp[xlen][ylen];

        if(x.charAt(xlen) == y.charAt(ylen)) return A + getMax(xlen + 1, ylen + 1);
        int score = MIN;
        score = Math.max(score, B + getMax(xlen + 1, ylen));
        score = Math.max(score, B + getMax(xlen, ylen + 1));
        score = Math.max(score, C + getMax(xlen + 1, ylen + 1));
        return dp[xlen][ylen] = score;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        x = br.readLine();
        y = br.readLine();
        dp = new int[x.length()][y.length()];
        for(int i = 0 ; i < x.length() ; i ++){
            Arrays.fill(dp[i], MIN);
        }

        System.out.println(getMax(0, 0));
    }
}
