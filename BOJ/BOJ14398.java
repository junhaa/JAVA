import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// BOJ #14398 피타고라스 수
public class Main {
    static int N;
    static ArrayList<Integer>[] edge;
    static boolean[] visited;
    static int[] aarr, barr;

    static boolean DFS(int cur){
        visited[cur] = true;
        for(int next : edge[cur]){
            if(barr[next] == -1 || !visited[barr[next]] && DFS(barr[next])){
                aarr[cur] = next;
                aarr[next] = cur;
                barr[next] = cur;
                barr[cur] = next;
                return true;
            }
        }
        return false;
    }
    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    static boolean canInteger(int a, int b){
        double tmp = Math.sqrt((long)a * a + (long)b * b);
        if(Math.floor(tmp) == tmp) return true;
        else return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] stick = new int[N];
        for(int i = 0 ; i < N ; i ++){
            stick[i] = Integer.parseInt(st.nextToken());
        }
        edge = new ArrayList[N];

        for(int i = 0 ; i < N ; i ++){
            edge[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < N ; i ++){
            for(int j = i + 1 ; j < N ; j ++){
                if(gcd(stick[i], stick[j]) == 1 && canInteger(stick[i], stick[j])) {
                    edge[i].add(j);
                    edge[j].add(i);
                }
            }
        }

        aarr = new int[N];
        barr = new int[N];
        Arrays.fill(aarr, -1);
        Arrays.fill(barr, -1);

        int answer = 0;
        for(int i = 0 ; i < N ; i ++){
            if(aarr[i] == -1){
                visited = new boolean[N];
                if(DFS(i)) answer ++;
            }
        }
        System.out.println(answer);
    }
}
