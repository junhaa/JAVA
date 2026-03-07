import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #30012 개구리 매칭
public class Main {
    private static final int NULL_MIN_INDEX = -1;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] positions = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            positions[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        System.out.println(main.solve(S, N, positions, K, L));
    }

    private String solve(int S, int N, int[] frogPositions, int K, int L) {
        int min = Integer.MAX_VALUE;
        int minIndex = NULL_MIN_INDEX;

        for(int i = 0 ; i < N ; i ++){
            int dis = Math.abs(frogPositions[i] - S);
            int curCost = getCost(dis, K, L);
            if(min > curCost) {
                min = curCost;
                minIndex = i + 1;
            }
        }

        return min + " " + minIndex;
    }

    private int getCost(int dis, int K, int L){
        if(K * 2 <= dis) {
            return (dis - (K * 2)) * L;
        }
        return K * 2 - dis;
    }
}
