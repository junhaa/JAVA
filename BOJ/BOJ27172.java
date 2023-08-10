import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #27172 수 나누기 게임
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        //HashMap<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int[] arr = new int[N];
        int[] score = new int[1_000_001];
        boolean[] ch = new boolean[1_000_001];
        for(int i = 0 ; i < N ; i ++){
            int tmp = Integer.parseInt(st.nextToken());
            arr[i] = tmp;
            max = Math.max(tmp, max);
            ch[tmp] = true;
        }
        for(int key : arr){
            for(int i = key * 2 ; i <= max ; i += key){
                if(ch[i]){
                    score[i] --;
                    score[key] ++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i ++){
            int tmp = arr[i];
            sb.append(score[tmp] + " ");
        }
        System.out.println(sb);
    }
}
