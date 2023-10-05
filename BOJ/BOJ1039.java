import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// BOJ #1039 교환
public class Main {

    static int BFS(int N, int K) {
        Queue<String> Q = new LinkedList<>();
        boolean visited[][] = new boolean[2][1_000_001];
        Q.offer(String.valueOf(N));
        int L = 0;
        while (!Q.isEmpty()) {
            int Qlen = Q.size();
            for (int k = 0; k < Qlen; k++) {
                String tmp = Q.poll();
                int len = tmp.length();
                for (int i = 0; i < len; i++) {
                    for (int j = i + 1; j < len; j++) {
                        char lt = tmp.charAt(i);
                        char rt = tmp.charAt(j);
                        if (i == 0 && rt == '0') continue;
                        char[] tmpArr = tmp.toCharArray();
                        char tmpC = lt;
                        tmpArr[i] = rt;
                        tmpArr[j] = tmpC;
                        String tmpStr = String.valueOf(tmpArr);
                        int ntmp = Integer.parseInt(tmpStr);
                        if (!visited[(L + 1) % 2][ntmp]) {
                            visited[(L + 1) % 2][ntmp] = true;
                            Q.offer(tmpStr);
                        }
                    }
                }
            }
            if(++L >= K) break;
        }
        for (int i = 1000000; i >= 0; i--) {
            if (visited[K % 2][i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(BFS(N, K));

    }
}
