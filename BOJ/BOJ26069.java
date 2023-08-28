import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #26069 붙임성 좋은 총총이
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<>();
        StringTokenizer st;
        set.add("ChongChong");
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            if(set.contains(a)) set.add(b);
            if(set.contains(b)) set.add(a);
        }
        System.out.println(set.size());
    }
}
