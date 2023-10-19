import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #19538 루머 
public class Main {
    static ArrayList<Integer>[] list;
    static int[] time;
    static int[] count, scount;

    static void BFS(Queue<Integer> Q){
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++) {
                int tmp = Q.poll();
                for(int next : list[tmp]){
                    if(time[next] != -1) continue;
                    if((double)--count[next] <= (double)scount[next] / 2){
                        Q.offer(next);
                        time[next] = L + 1;
                    }
                }
            }
            L ++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        time = new int[N];
        count = new int[N];
        scount = new int[N];
        Arrays.fill(time, -1);
        StringTokenizer st;
        list = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++){
            list[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            while(true){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp != 0){
                    list[i].add(tmp - 1);
                    count[i] ++;
                }
                else{
                    break;
                }
            }
        }

        System.arraycopy(count, 0, scount, 0, N);
        Queue<Integer> Q = new LinkedList<>();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i ++){
            int cur = Integer.parseInt(st.nextToken());
            Q.offer(cur - 1);
            time[cur - 1] = 0;
        }
        BFS(Q);
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N; i ++){
            sb.append(time[i] + " ");
        }

        System.out.println(sb);
    }
}
