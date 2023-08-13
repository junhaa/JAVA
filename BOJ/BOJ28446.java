import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// BOJ #28446 볼링공 찾아주
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        HashMap<Integer, Integer> map = new HashMap<>();
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i ++){
            st  = new StringTokenizer(br.readLine());
            int Q = Integer.parseInt(st.nextToken());
            if(Q == 1){
                int idx = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                map.put(weight, idx);
            }
            else{
                int weight = Integer.parseInt(st.nextToken());
                sb.append(map.get(weight) + "\n");
            }
        }
        System.out.println(sb);
    }
}
