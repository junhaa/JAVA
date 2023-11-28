import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// BOJ #12843 복수전공 
public class Main {
    static int N, M;
    static boolean[] visited;
    static ArrayList<Integer>[] edge;
    static int[] aarr, barr;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(barr[next] == -1 || !visited[barr[next]] && DFS(barr[next])){
                aarr[cur] = next;
                barr[next] = cur;
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> map = new HashMap<>();
        int alen = 0;
        for(int i = 0 ; i < N ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            char b = st.nextToken().charAt(0);
            if(b == 'c'){
                map.put(a, alen ++);
            }
        }

        aarr = new int[alen];
        barr = new int[N + 1];
        Arrays.fill(aarr, -1);
        Arrays.fill(barr, -1);

        edge = new ArrayList[alen];
        for(int i = 0 ; i < alen ; i ++){
            edge[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(!map.containsKey(start)){
                int tmp = start;
                start = end;
                end = tmp;
            }
            edge[map.get(start)].add(end);
        }

        int answer = 0;

        for(int i = 0 ; i < alen ; i ++){
            if(aarr[i] == -1){
                visited = new boolean[alen];
                if(DFS(i)) answer ++;
            }
        }
        System.out.println(N - answer);
    }
}
