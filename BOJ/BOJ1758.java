import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
// BOJ #1758 알바생 강호
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr, Collections.reverseOrder());
        long answer = 0;
        for(int i = 0 ; i < N ; i ++){
            answer += arr[i] - i < 0 ? 0 : arr[i] - i;
        }
        System.out.println(answer);
    }
}
