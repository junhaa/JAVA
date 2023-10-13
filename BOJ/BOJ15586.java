import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #15586 MooTube(Gold)
class Query implements Comparable<Query> {
    int idx, k, v;

    public Query(int idx, int k, int v) {
        this.idx = idx;
        this.k = k;
        this.v = v;
    }
    @Override
    public int compareTo(Query O){
        return O.k - this.k;
    }
}
class Edge implements Comparable<Edge>{
    int start, end, cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge O){
        return O.cost - this.cost;
    }
}
public class Main {
    static int[] parent, cnt, answer;
    static ArrayList<Edge> list = new ArrayList<>();
    static ArrayList<Query> qlist = new ArrayList<>();

    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(fa != fb) {
            if (fa < fb) {
                parent[fb] = fa;
                cnt[fb] ++;
                cnt[fa] += cnt[fb];

            }
            else{
                parent[fa] = fb;
                cnt[fa] ++;
                cnt[fb] += cnt[fa];
            }
        }
    }

    static void link(int N, int Q){
        if(N == 1) return;
        int cur = list.get(0).cost;
        int qidx = 0;
        int next = 0;
        int eidx = 0;

        int start = 0;
        int end = 0;
        while(true) {
            while (next < N - 1) {
                if (cur > list.get(next).cost) {
                    end = next;
                    break;
                }
                next++;
                if(next == N - 1){
                    end = next;
                    break;
                }
            }
            while (qidx < Q && qlist.get(qidx).k > cur) {
                Query curQ = qlist.get(qidx);
                answer[curQ.idx] = cnt[find(curQ.v)];
                qidx++;
            }
            if(next < N - 1) cur = list.get(next).cost;
            int len = end - start;
            start = next;
            for (int i = 0; i < len; i++) {
                Edge curE = list.get(eidx);
                union(curE.start, curE.end);
                eidx++;
                if(eidx == N - 1) {
                    while(qidx < Q){
                        Query curQ = qlist.get(qidx);
                        answer[curQ.idx] = cnt[find(curQ.v)];
                        qidx ++;
                    }
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        parent = new int[N];
        cnt = new int[N];
        answer = new int[Q];

        //Arrays.fill(cnt, 1);
        for(int i = 0 ; i < N ; i ++){
            parent[i] = i;
        }

        for(int i = 0 ; i < N - 1 ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Edge(start, end, cost));
        }
        Collections.sort(list);

        for(int i = 0 ; i < Q ; i ++){
            st = new StringTokenizer(br.readLine());
            qlist.add(new Query(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1));
        }
        Collections.sort(qlist);

        link(N, Q);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < Q ; i ++){
            sb.append(answer[i] + "\n");
        }
        System.out.println(sb);
    }
}
