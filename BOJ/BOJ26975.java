import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #26975 Cow College
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Collections.reverseOrder());
        long max = Long.MIN_VALUE;
        int cnt = -1;
        for(int i = 0 ; i < N ; i ++){
            long sum = (long)arr[i] * (i + 1);
            if(sum >= max){
                max = sum;
                cnt = arr[i];
            }
        }
        System.out.println(max + " " + cnt);
    }
}
