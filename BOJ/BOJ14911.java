import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #14911 궁합 쌍 찾기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int len = st.countTokens();
        int[] arr = new int[len];
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < len ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int answer = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i = 0 ; i < len ; i ++){
            HashSet<Integer> set = new HashSet<>();
            for(int j = i + 1 ; j < len ; j ++){
                if(arr[i] + arr[j] == answer && !set.contains(arr[j])){
                    set.add(arr[j]);
                    sb.append(arr[i] + " " + arr[j] + "\n");
                    cnt ++;
                }
            }
        }
        sb.append(cnt);
        System.out.println(sb);
    }
}
