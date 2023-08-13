import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ 28445 알록달록 앵무새
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i ++){
            st = new StringTokenizer(br.readLine());
            list.add(st.nextToken());
            list.add(st.nextToken());
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        for(int i = 0 ; i < 4 ; i ++){
            for(int j = 0 ; j < 4 ; j ++){
                String tmp = list.get(i) + " " + list.get(j);
                if(!set.contains(tmp)) {
                    sb.append(list.get(i) + " " + list.get(j) + "\n");
                    set.add(tmp);
                }
            }
        }
        System.out.println(sb);
    }
}
