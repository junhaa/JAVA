import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #4902 삼각형의 값
public class Main {
    static ArrayList<int[]> list;

    static int getSum(int start, int end, int[] prefix){
        return prefix[end] - prefix[start - 1];
    }
    static int getTriangle(int N){
        int max = Integer.MIN_VALUE;
        for(int j = 1 ; j <= N - 1 ; j ++){ // 높이
            for(int k = 1 ; k <= 2 * j - 1 ; k += 2){ // 너비
                int sum = 0;
                for(int l = 0 ; l <= N - j ; l ++){
                    int[] prefix = list.get(j + l);
                    int tmpSum = getSum(k, k + 2 * l, prefix);
                    sum += tmpSum;
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }

    static int getRTriangle(int N){
        int max = Integer.MIN_VALUE;
        for(int k = N ; k >= 1 ; k --){ // 높이
            for(int j = 4 ; j <= 2 * k - 3 ; j += 2){ // 너비
                int sum = 0;
                for(int l = 0 ; l < Math.min(j / 2, (2 * k + 1 - j) / 2) ; l ++){
                    int[] prefix = list.get(k - l);
                    int tmpSum = getSum(j - 2 * l, j, prefix);
                    sum += tmpSum;
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException  {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            if(st.countTokens() == 1) break;
            sb.append(cnt + ". ");
            cnt ++;
            int max = Integer.MIN_VALUE;
            list = new ArrayList<>();
            list.add(null);
            int N = Integer.parseInt(st.nextToken());
            for(int i = 0 ; i < N ; i ++){
                int[] tmp = new int[i * 2 + 1 + 1];
                for(int j = 1 ; j <= i * 2 + 1 ; j ++) {
                    int tNum = Integer.parseInt(st.nextToken());
                    max = Math.max(max, tNum);
                    tmp[j] = tmp[j - 1] + tNum;
                }
                list.add(tmp);
            }
            max = Math.max(max, getTriangle(N));
            max = Math.max(max, getRTriangle(N));
            sb.append(max + "\n");
        }
        System.out.println(sb);
    }
}
