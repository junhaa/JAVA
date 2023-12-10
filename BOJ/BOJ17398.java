import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #17398 통신망 분할
class Edge{
    int a, b;
    public Edge(int start, int end) {
        this.a = start;
        this.b = end;
    }
}
public class Main {
    static int[] parent, size;
    static int find(int x){
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    static long union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        int ret = 0;
        if(fa < fb){
            parent[fb] = fa;
            if(fa != fb){
                ret += (long)size[fa] * size[fb];
                size[fa] += size[fb];
            }
        }
        else{
            parent[fa] = fb;
            if(fa != fb) {
                ret += (long)size[fa] * size[fb];
                size[fb] += size[fa];
            }
        }
        return ret;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ArrayList<Edge> list = new ArrayList<>();
        boolean[] check = new boolean[M]; // 자르는 간선인지 체크
        int[] cut = new int[Q];
        parent = new int[N];
        size = new int[N];
        Arrays.fill(size, 1);
        for(int i = 0 ; i < N ; i ++){
            parent[i] = i;
        }

        long answer = 0;

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 0 - based
            int b = Integer.parseInt(st.nextToken()) - 1;
            list.add(new Edge(a, b));
        }

        for(int i = 0 ; i < Q ; i ++){
            int tmp = Integer.parseInt(br.readLine()) - 1;
            check[tmp] = true;
            cut[i] = tmp;
        }

        for(int i = 0 ; i < M ; i ++){
            if(!check[i]){
                Edge cur = list.get(i);
                union(cur.a, cur.b);
            }
        }

        for(int i = Q - 1 ; i >= 0 ; i --){
            Edge cur = list.get(cut[i]);
            answer += union(cur.a, cur.b);
        }
        System.out.println(answer);
    }
}
