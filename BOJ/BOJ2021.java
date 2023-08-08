import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #2021 최소 환승 경로
public class Main {
    static int N, L;
    static ArrayList<Integer>[] edge;

    static int BFS(int start, int end){
        if(start == end) return 0;
        boolean[] visited = new boolean[N + L];
        visited[start] = true;
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(start);
        int L = 0;
        while(!Q.isEmpty()){
            int len = Q.size();
            if(Q.peek() >= N) L ++;
            for(int i = 0 ; i < len ; i ++){
                int tmp = Q.poll();
                if(tmp == end) return L - 1;
                for(int next : edge[tmp]){
                    if(!visited[next]){
                        visited[next] = true;
                        Q.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 역의 수
        L = Integer.parseInt(st.nextToken()); // 노선의 개수
        edge = new ArrayList[N + L];
        for(int i = 0 ; i < N + L ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < L ; i ++){
            st = new StringTokenizer(br.readLine());
            while(true){
                int tmp = Integer.parseInt(st.nextToken()) - 1;
                if(tmp == -2) break;
                edge[N + i].add(tmp);
                edge[tmp].add(N + i);
            }
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        System.out.println(BFS(start, end));
    }
}
