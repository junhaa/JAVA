import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #17088 등차수열 변환
public class Main {
    static int min = Integer.MAX_VALUE, N;
    static int[] arr;
    static void recursive(int cnt, int preDif, int preSum, int idx){
        if(idx == N){
            min = Math.min(min, cnt);
            return;
        }
        if(preDif == (arr[idx - 1] + preSum) - (arr[idx] + 1)){
            recursive(cnt + 1, preDif, 1, idx + 1);
        }
        else if(preDif == (arr[idx - 1] + preSum) - (arr[idx] - 1)){
            recursive(cnt + 1, preDif, -1, idx + 1);
        }
        else if(preDif == (arr[idx - 1] + preSum) - arr[idx]){
            recursive(cnt, preDif, 0, idx + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       N = Integer.parseInt(br.readLine());
        if(N == 1 || N == 2){
            System.out.println(0);
            return;
        }
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = -1 ; i <= 1 ; i ++){
            for(int j = -1 ; j <= 1 ; j ++){
                int n1 = arr[0] + i;
                int n2 = arr[1] + j;
                int cnt = 0;
                if(i != 0) cnt ++;
                if(j != 0) cnt ++;
                recursive(cnt, n1 - n2, j, 2);
            }
        }
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
}
