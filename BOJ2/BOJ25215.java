import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BOJ #25215 타이핑
public class Main {
    private static final int CLICK_RHOMBUS_BUTTON = 1;
    private static final int CLICK_STAR_BUTTON = 1;
    private static final int CLICK_ALPHABET_BUTTON = 1;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        System.out.println(main.solve(input));
    }

    private int solve(String input){
        int length = input.length();
        int[][] dp = new int[2][length + 1]; // 마름모 버튼 활성화 여부, 각 문자 index

        for(int i = 0 ; i < 2 ; i ++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;
        dp[1][0] = 1;

        for(int i = 1 ; i <= length ; i ++){

            char cur = input.charAt(i - 1); // 0-based

            if(Character.isUpperCase(cur)) {
                dp[0][i] = Math.min(
                        dp[0][i - 1] + CLICK_ALPHABET_BUTTON + CLICK_STAR_BUTTON,
                        dp[1][i - 1] + CLICK_ALPHABET_BUTTON + CLICK_RHOMBUS_BUTTON
                );

                dp[1][i] = Math.min(
                        dp[0][i - 1] + CLICK_RHOMBUS_BUTTON + CLICK_ALPHABET_BUTTON,
                        dp[1][i - 1] + CLICK_ALPHABET_BUTTON
                );
            }
            else {
                dp[0][i] = Math.min(
                        dp[0][i - 1] + CLICK_ALPHABET_BUTTON,
                        dp[1][i - 1] + CLICK_ALPHABET_BUTTON + CLICK_RHOMBUS_BUTTON
                );

                dp[1][i] = Math.min(
                        dp[0][i - 1] + CLICK_RHOMBUS_BUTTON + CLICK_ALPHABET_BUTTON,
                        dp[1][i - 1] + CLICK_ALPHABET_BUTTON + CLICK_STAR_BUTTON
                );
            }
        }

        return Math.min(dp[0][length], dp[1][length]);
    }
}
