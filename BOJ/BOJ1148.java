import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
// BOJ #1148 단어 만들기 
public class Main {

    static boolean check(int[] answer, int[][] set, int i){
            for(int j = 0 ; j < 26 ; j ++){
                if(answer[j] < set[j][i]){
                    return false;
                }
            }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] sum = new int[26];
        int[][] set = new int[26][200000];
        int idx = 0;
        while(true){
            String str = br.readLine();
            if(str.equals("-")) break;
            for(int i = 0 ; i < str.length() ; i ++){
                char curChar = str.charAt(i);
                set[curChar - 'A'][idx] ++;
            }
            idx ++;
        }

        StringBuilder sb = new StringBuilder();


        while(true){
            String cur = br.readLine();
            int[] answer = new int[26];
            if(cur.equals("#")) break;
            Arrays.fill(sum, -1);
            for(int i = 0 ; i < 9 ; i ++){
                sum[cur.charAt(i) - 'A'] = 0;
                answer[cur.charAt(i) - 'A'] ++;
            }

            for(int i = 0 ; i < idx ; i ++){
                if(!check(answer, set, i)) continue;
                for(int j = 0 ; j < 26 ; j ++){
                    if(set[j][i] != 0){
                        sum[j] ++;
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i = 0 ; i < 26 ; i ++){
                if(sum[i] != -1) {
                    min = Math.min(min, sum[i]);
                    max = Math.max(max, sum[i]);
                }
            }

            String minStr = "";
            String maxStr = "";

            for(int i = 0 ; i < 26 ; i ++){
                if(sum[i] != -1) {
                    if (sum[i] == min) {
                        minStr += (char) (i + 'A');
                    }
                    if (sum[i] == max) {
                        maxStr += (char) (i + 'A');
                    }
                }
            }
            sb.append(minStr + " " + min + " " + maxStr + " " + max + "\n");
        }
        System.out.println(sb);
    }
}
