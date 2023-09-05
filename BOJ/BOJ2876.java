import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #2876 그래픽스 퀴즈 
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        StringTokenizer st;
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int b = Integer.parseInt(st.nextToken()) - 1;
            arr[i][0] = a;
            arr[i][1] = b;
        }
        int lastA = -1, lastB = -1;
        int[] cnt = new int[5];
        int max = Integer.MIN_VALUE;
        int num = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < 2 ; j ++) {
                if(j == 1 && arr[i][0] == arr[i][1]) continue;
                int tmp = arr[i][j];
                if (lastA == tmp || lastB == tmp) {
                    cnt[tmp]++;

                } else {
                    cnt[tmp] = 1;
                }
                if (cnt[tmp] > max) {
                    num = tmp;
                    max = cnt[tmp];
                } else if (cnt[tmp] == max && tmp < num) {
                    num = tmp;
                }
            }
            lastA = arr[i][0];
            lastB = arr[i][1];
        }
        System.out.println(max + " " + (num + 1));
    }
}
