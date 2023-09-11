import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #6213 Balanced Lineup
class Node{
    int max, min;

    public Node(int max, int min) {
        this.max = max;
        this.min = min;
    }
}

public class Main {
    static Node[] tree;
    static int size = 2;

    static void construct(){
        for(int i = size / 2 - 1 ; i > 0 ; i --){
            tree[i] = new Node(Math.max(tree[i * 2].max, tree[i * 2 + 1].max), Math.min(tree[i * 2].min, tree[i * 2 + 1].min));
        }
    }

    static int getMin(int L, int R, int nodeNum, int nodeL, int nodeR){
        if(R < nodeL || nodeR < L) return Integer.MAX_VALUE;
        if(L <= nodeL && nodeR <= R) return tree[nodeNum].min;
        int mid = (nodeL + nodeR) / 2;
        return Math.min(getMin(L, R, nodeNum * 2, nodeL, mid), getMin(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
    }

    static int getMax(int L, int R, int nodeNum, int nodeL, int nodeR){
        if(R < nodeL || nodeR < L) return Integer.MIN_VALUE;
        if(L <= nodeL && nodeR <= R) return tree[nodeNum].max;
        int mid = (nodeL + nodeR) / 2;
        return Math.max(getMax(L, R, nodeNum * 2, nodeL, mid), getMax(L, R, nodeNum * 2 + 1, mid + 1, nodeR));
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        while(true){
            if(size >= N) {
                size *= 2;
                break;
            }
            size *= 2;
        }
        tree = new Node[size];
        for(int i = 0 ; i < size / 2 ; i ++){
            if(i < N) {
                int tmp = Integer.parseInt(br.readLine());
                tree[i + size / 2] = new Node(tmp, tmp);
            }
            else{
                tree[i + size / 2] = new Node(Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        construct();
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < Q ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int max = getMax(start, end, 1, 0, size / 2 -1 );
            int min = getMin(start, end, 1, 0 , size / 2 - 1);
            sb.append((max - min) + "\n");
        }
        System.out.println(sb);
    }
}
