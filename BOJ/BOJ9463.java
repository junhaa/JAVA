import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// BOJ #9463 순열 그래프
public class Main {
    static int[] tree;
    static int size;

    static int getSum(int L, int R, int nodeNum, int nodeL, int nodeR){
        if(R < nodeL || nodeR < L) return 0;
        if(L <= nodeL && nodeR <= R) return tree[nodeNum];
        int mid = (nodeL + nodeR) / 2;
        return getSum(L, R, nodeNum * 2, nodeL, mid) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR);
    }

    static void update(int idx){
        idx += size / 2;
        tree[idx] ++;
        while(idx > 1){
            idx /= 2;
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(T -- > 0){
            int n = Integer.parseInt(br.readLine());
            HashMap<Integer, Integer> map = new HashMap<>();
            size = 2;
            while(true){
                if(size >= n){
                    size *= 2;
                    break;
                }
                size *= 2;
            }

            tree = new int[size];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < n ; i ++){
                map.put(Integer.parseInt(st.nextToken()), i);
            }

            st = new StringTokenizer(br.readLine());

            long answer = 0;
            for(int i = 0 ; i < n ; i ++){
                int idx = map.get(Integer.parseInt(st.nextToken()));
                answer += getSum(idx + 1, n - 1, 1, 0, size / 2 - 1);
                update(idx);
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
}
