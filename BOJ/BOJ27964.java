import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #27964 콰트로치즈피자
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<String> set = new HashSet<>();
        for(int i = 0 ; i < N ; i ++){
            String cur = st.nextToken();
            if(cur.length() >= 6 && cur.substring(cur.length() - 6, cur.length()).equals("Cheese")){
                if(set.contains(cur)) continue;
                else{
                    set.add(cur);
                }
            }
        }
        if(set.size() >= 4){
            System.out.println("yummy");
        }
        else System.out.println("sad");
    }
}
