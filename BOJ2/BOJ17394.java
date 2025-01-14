import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
// BOJ #17394 핑거 스냅
public class Main {
    private static final int PRIME_RANGE = (int)1e6;
    private static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        checkPrime();
        StringTokenizer st;
        while(T -- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(solve(N, A, B)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(int N, int A, int B){
        Set<Integer> primeSet = new HashSet<>();
        for(int i = A ; i <= B ; i ++){
            if(isPrime[i]) primeSet.add(i);
        }

        if(primeSet.isEmpty()) return -1;

        boolean[] visited = new boolean[(int)1e7 + 1];


        int L = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N] = true;
        while(!q.isEmpty()){
            int len = q.size();
            for(int i = 0 ; i < len ; i ++){
                int cur = q.poll();
                if(primeSet.contains(cur)) return L;
                int next;
                next = cur / 2;
                addQueue(q, next, visited);
                next = cur / 3;
                addQueue(q, next, visited);
                next = cur + 1;
                addQueue(q, next, visited);
                next = cur - 1;
                addQueue(q, next, visited);
            }
            L ++;
        }
        return -1;
    }
    private static void addQueue(Queue<Integer> q, int next, boolean[] visited){
        if(next >= 0 && next < visited.length && !visited[next]){
            visited[next] = true;
            q.add(next);
        }
    }


    private static void checkPrime(){
        isPrime = new boolean[PRIME_RANGE + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2 ; i <= (int)Math.sqrt(PRIME_RANGE) ; i ++){
            for(int j = i * i ; j <= PRIME_RANGE ; j += i){
                isPrime[j] = false;
            }
        }
    }
}
