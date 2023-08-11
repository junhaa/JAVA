import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
// BOJ #23889 돌 굴러가유
class Stone implements Comparable<Stone>{
    int idx, cnt;
    public Stone(int idx, int cnt){
        this.idx = idx;
        this.cnt = cnt;
    }
    @Override
    public int compareTo(Stone o){
        return o.cnt - this.cnt;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int[] stone = new int[K];
        for(int i = 0 ; i < K ; i ++){
            stone[i] = Integer.parseInt(st.nextToken()) - 1; // 0-based
        }
        ArrayList<Stone> list = new ArrayList<>();
        for(int i = 0 ; i < K ; i ++){
            int start = stone[i];
            int sum = 0;
            for(int k = start ; k < N ; k ++){
                sum += arr[k];
                if(k != N - 1 && i != K - 1 && k + 1 == stone[i + 1]){
                    break;
                }
            }
            list.add(new Stone(start, sum));
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        int[] answerArr = new int[M];
        for(int i = 0 ; i < M ; i ++){
            answerArr[i] = list.get(i).idx;
        }
        Arrays.sort(answerArr);
        for(int ans : answerArr){
            sb.append((ans + 1) + "\n");
        }
        System.out.println(sb);
    }
}
