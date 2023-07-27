import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #13144 List of Unique Numbers
public class Main {
    static int N;
    static int[] arr;
    static boolean[] ch = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long answer = 0;
        int lt = 0;
        int rt = 0;
        while(rt < N){
             if(ch[arr[rt]]){
                while(true){
                    answer += rt - lt;
                    ch[arr[lt++]] = false;
                    if(!ch[arr[rt]]) break;
                }
            }
            ch[arr[rt++]] = true;
        }
        /*
        while(true){
            if(ch[arr[rt]]) {
                answer += (rt - lt) * (rt - lt - 1) / 2;
                while(true){
                    ch[arr[lt ++]] = false;
                    if(!ch[arr[rt]]) break;
                }
            }
            ch[arr[rt]] = true;
            if(rt == N - 1) break;
            rt ++;
        }

         */
        rt --;
        answer += ((long)rt - (long)lt + 1) * ((long)rt - (long)lt  + 2) / 2;
        System.out.println(answer);
    }
}
