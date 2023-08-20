import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
// BOJ #1765 닭싸움 팀 정하기 
public class Main {
    static int N;
    static int[] parent;
    static ArrayList<Integer>[] edge;
    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(fa < fb){
            parent[fb] = fa;
        }
        else parent[fa] = fb;
    }

    static void unionEnemy(){
        for(int i = 0 ; i < N ; i ++){
            for(int e : edge[i]){
                for(int f : edge[e]){
                    union(f, i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        parent = new int[N];
        edge = new ArrayList[N];
        for(int i = 0 ; i < N ; i ++) {
            edge[i] = new ArrayList<>();
            parent[i] = i;
        }
        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            if(st.nextToken().charAt(0) == 'E'){
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                edge[start].add(end);
                edge[end].add(start);
            }
            else{
                union(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            }
        }
        unionEnemy();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0 ; i < N ; i ++){
            set.add(find(i));
        }
        System.out.println(set.size());
    }
}
