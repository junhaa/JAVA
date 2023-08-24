import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #20438 출석체크 
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<Integer> sleep = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 3];
        int[] prefix = new int[N + 4];
        for(int i = 0 ; i < K ; i ++){
            sleep.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < Q ; i ++){
            int tmp = Integer.parseInt(st.nextToken());
            if(sleep.contains(tmp)) continue;
            for(int j = tmp ; j <= N + 2 ; j += tmp){
                if(!sleep.contains(j)) arr[j] = 1;
            }
        }

        for(int i = 4 ; i <= N + 3 ; i ++){
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(end + 1 - start - prefix[end + 1] + prefix[start] + "\n");
        }
        System.out.println(sb);

    }
}
