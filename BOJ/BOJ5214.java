import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #5214 환승 
public class Main {
    static int N, M, K;
    static ArrayList<Integer>[] edge;

    static int BFS(){
        boolean[] visited = new boolean[N + M];
        visited[0] = true;
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(0);
        int L = 1;
        while(!Q.isEmpty()){
            int len = Q.size();
            for(int i = 0 ; i < len ; i ++){
                int tmp = Q.poll();
                if(tmp == N - 1) return L;
                for(int next : edge[tmp]){
                    if(!visited[next]){
                        visited[next] = true;
                        Q.offer(next);
                    }
                }
            }
            if(!Q.isEmpty() && Q.peek() >= N) L --;
            L ++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 역의 수
        K = Integer.parseInt(st.nextToken()); // 하이퍼튜브가 연결하는 역의 개수
        M = Integer.parseInt(st.nextToken()); // 하이퍼튜브의 개수
        edge = new ArrayList[N + M];
        for(int i = 0 ; i < N + M ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < K ; j ++){
                int tmp = Integer.parseInt(st.nextToken()) - 1;
                edge[N + i].add(tmp);
                edge[tmp].add(N + i);
            }
        }
        System.out.println(BFS());
    }
}
