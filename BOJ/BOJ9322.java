import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// BOJ #9322 철벽 보안 알고리즘
public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        StringTokenizer st, st2;
        while(test -- > 0){
            int N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            String[] answer = new String[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i ++){
                map.put(st.nextToken(), i);
            }
            st = new StringTokenizer(br.readLine());
            st2 = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i ++){
                answer[map.get(st.nextToken())] = st2.nextToken();
            }
            for(String str : answer){
                sb.append(str + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
