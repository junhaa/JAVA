import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #12018 Yonsei TOTO
public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Integer> alist = new ArrayList<>();
        for(int i = 0 ; i < n ; i ++){
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> list = new ArrayList<>();
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            if(l > p) {
                alist.add(1);
                continue;
            }
            for(int j = 0 ; j < p ; j ++){
                list.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(list, Collections.reverseOrder());
            alist.add(list.get(l - 1));
        }
        Collections.sort(alist);
        int sum = 0;
        int cnt = 0;
        for(int i = 0 ; i < alist.size(); i ++){
            if(sum + alist.get(i) > K){
                break;
            }
            sum += alist.get(i);
            cnt ++;
        }
        System.out.println(cnt);
    }
}
