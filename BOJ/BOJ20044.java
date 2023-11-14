import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// BOJ #20044 Project Teams
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N * 2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N * 2 ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for(int i = 0 ; i < N ; i ++){
            min = Math.min(min, arr[i] + arr[2 * N - i - 1]);
        }
        System.out.println(min);
    }
}
