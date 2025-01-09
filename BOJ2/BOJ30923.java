import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #30923 크냑과 3D 프린터

public class Main {
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];

        long sum = 2 * N;

        for(int i = 0 ; i < N ; i ++){
            int cur = Integer.parseInt(st.nextToken());
            sum += cur * 4;
            arr[i] = cur;
        }

        for(int i = 0 ; i < N - 1 ; i ++){
            int min = Math.min(arr[i], arr[i + 1]);
            sum -= min * 2;
        }

        System.out.println(sum);
    }
}
