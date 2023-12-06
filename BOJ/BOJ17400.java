import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #17400 깃발춤
public class Main {
    static int N, size0 = 2, size1 = 2;
    static long[] tree0, tree1;
    static int getSize(int size, int tmp){
        while(true){
            if(size >= tmp){
                size *= 2;
                break;
            }
            size *= 2;
        }
        return size;
    }

    static void init(long[] tree, int size){
        for(int i = size / 2 - 1 ; i > 0 ; i --){
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static void update(long[] tree, int size, int i, int val){
        i += size / 2;
        tree[i] += val;
        while(i > 1){
            i /= 2;
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long getSum(int L, int R, int nodeNum, int nodeL, int nodeR, long[] tree, int size){
        if(R < nodeL || nodeR < L) return 0;
        if(R >= nodeR && nodeL >= L) return tree[nodeNum];
        int mid = (nodeR + nodeL) / 2;
        return getSum(L, R, nodeNum * 2, nodeL, mid, tree, size) + getSum(L, R, nodeNum * 2 + 1, mid + 1, nodeR, tree, size);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int tmp0 = N / 2, tmp1 = N / 2;
        if(N % 2 == 1){
            tmp0 ++;
        }
        size0 = getSize(size0, tmp0);
        size1 = getSize(size1, tmp1);

        tree0 = new long[size0];
        tree1 = new long[size1];
        st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N ; i ++){
            long cur = Long.parseLong(st.nextToken());
            int size;
            long[] curTree;
            if(i % 2 == 0){
                curTree = tree0;
                size = size0;
            }
            else{
                curTree = tree1;
                size = size1;
            }
            int idx = i / 2 + (size / 2);
            curTree[idx] = cur;
        }

        init(tree0, size0);
        init(tree1, size1);
        for(int i = 0 ; i < Q ; i ++){
            st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 1){
                int L = (Integer.parseInt(st.nextToken()) - 1);
                int R = (Integer.parseInt(st.nextToken()) - 1);
                long sum0 = getSum(L % 2 == 0 ? L / 2 : L / 2 + 1, R / 2, 1, 0, size0 / 2 - 1, tree0, size0);
                long sum1 = getSum(L / 2, R % 2 == 1 ? R / 2 : R / 2 - 1, 1, 0, size1 / 2 - 1, tree1, size0);
                sb.append(Math.abs(sum0 - sum1) + "\n");
            }
            else{
                int L = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken());
                if(L % 2 == 0){
                    update(tree0, size0, L / 2, v);
                }
                else{
                    update(tree1, size1, L / 2, v);
                }
            }
        }
        System.out.println(sb);
    }
}
