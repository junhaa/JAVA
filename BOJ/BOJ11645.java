import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
// BOJ #11645 I’ve Been Everywhere, Man
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T -- > 0){
            HashSet<String> set = new HashSet<>();
            int N = Integer.parseInt(br.readLine());
            for(int i = 0 ; i < N ; i ++){
                String cur = br.readLine();
                if(!set.contains(cur)){
                    set.add(cur);
                }
            }
            sb.append(set.size() + "\n");
        }
        System.out.println(sb);
    }
}
