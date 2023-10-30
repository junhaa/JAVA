import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #16200 해커톤
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] arr = new Integer[N];
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Collections.reverseOrder());
        int cnt = 0;
        int team = 0;
        for(int i = 0 ; i < N ; i ++){
            int cur = arr[i];
            if(cur > cnt) cnt ++;
            else{
                team ++;
                cnt = 1;
            }
        }
        team ++;
        System.out.println(team);
    }
}
