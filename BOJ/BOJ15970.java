import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #15970 화살표 그리기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer>[] dot;
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        dot = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++){
            dot[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < N; i ++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken()) - 1;
            dot[y].add(x);
        }
        for(int i = 0 ; i < N ; i ++){
            Collections.sort(dot[i]);
        }

        int sum = 0;

        for(int i = 0 ; i < N ; i ++){
            if(dot[i].isEmpty()) continue;
            int last = dot[i].get(0);
            sum += dot[i].get(1) - last;
            for(int j = 1 ; j < dot[i].size() - 1 ; j ++){
                int cur = dot[i].get(j);
                sum += Math.min(Math.abs(cur - last), Math.abs(cur - dot[i].get(j + 1)));
                last = cur;
            }
            sum += dot[i].get(dot[i].size() - 1) - last;
        }
        System.out.println(sum);
    }
}
