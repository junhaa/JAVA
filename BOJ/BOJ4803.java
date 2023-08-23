import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #4803 트리
public class Main {

    static int find(int x, int[] parent){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x], parent);
    }

    static boolean union(int a, int b, int[] parent){
        int fa = find(a, parent);
        int fb = find(b, parent);
        if(fa == fb) return false;
        if(fa < fb){
            parent[fb] = fa;
        }
        else parent[fa] = fb;
        return true;
    }

    static void DFS(int last, int cur, ArrayList<Integer>[] edge, int[] parent){
        for(int next : edge[cur]){
            if(next == last) continue;
            if(!union(cur, next, parent)) {
                union(0, cur, parent);
                continue;
            }
            DFS(cur, next, edge, parent);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while(true){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;
            sb.append("Case " + cnt++ + ": ");
            ArrayList<Integer>[] edge = new ArrayList[N + 1];
            int[] parent = new int[N + 1];
            for(int i = 1 ; i <= N ; i ++) {
                edge[i] = new ArrayList<>();
                parent[i] = i;
            }
            for(int i = 0 ; i < M ; i ++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                edge[start].add(end);
                edge[end].add(start);
            }
            for(int i = 1 ; i <= N ; i ++) {
                if(parent[i] == i) {
                    DFS(-1, i, edge, parent);
                }
            }
            HashSet<Integer> set = new HashSet<>();
            for(int i = 1 ; i <= N ; i ++){
                int ftmp = find(i, parent);
                if(ftmp != 0) set.add(ftmp);
            }
            if(set.size() > 1){
                sb.append("A forest of " + set.size() + " trees.\n");
            }
            else if(set.size() == 1){
                sb.append("There is one tree.\n");
            }
            else {
                sb.append("No trees.\n");
            }
        }
        System.out.println(sb);
    }
}
