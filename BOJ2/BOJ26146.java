import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #26146 즉흥 여행 (Easy)
public class Main {
    private static int N;
    private static List<Integer>[] edges;
    private static int[] sn;
    private static int[] dfsn;
    private static int cnt;
    private static int snCount;
    private static boolean[] finished;

    private int getSCC(int i, Stack<Integer> stack) {
        dfsn[i] = ++cnt;
        stack.push(i);

        int result = dfsn[i];
        for(int next : edges[i]) {
            if(dfsn[next] == 0) result = Math.min(getSCC(next, stack), result);
            else if(!finished[next]) result = Math.min(dfsn[next], result);
        }

        if(result == dfsn[i]) {
            while(true) {
                int tmp = stack.pop();
                sn[tmp] = snCount;
                finished[tmp] = true;
                if(tmp == i) break;
            }
            snCount++;
        }
        return result;
    }

    private void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        sn = new int[N];
        dfsn = new int[N];
        cnt = 0;
        snCount = 0;
        edges = new List[N];
        finished = new boolean[N];

        for(int i = 0 ; i < N ; i ++){
            edges[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M ; i ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            edges[start].add(end);
        }
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();

        main.init();

        for(int i = 0 ; i < N ; i ++){
            if(dfsn[i] == 0) {
                main.getSCC(i, new Stack<>());
            }
        }
        System.out.println(main.isMovable() ? "Yes" : "No");
    }

    private boolean isMovable() {
        for(int i = 0 ; i < N ; i ++){
            if(sn[i] != 0){
                return false;
            }
        }
        return true;
    }
}


