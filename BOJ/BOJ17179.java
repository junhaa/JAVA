import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// BOJ #17179 케이크 자르기 
public class Main {
    static int binarySearch(int[] cut, int M, int cutCnt, int L){
        int lt = 0;
        int rt = L;
        int max = Integer.MIN_VALUE;
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            int cnt = 0;
            int last = 0;
            for(int i = 0 ; i < M ; i ++){
                if(cut[i] - last < mid) continue;
                last = cut[i];
                if(++cnt == cutCnt) break;
            }
            if(cnt >= cutCnt){
                if(L - last < mid);
                else {
                    max = Math.max(max, mid);
                    lt = mid + 1;
                    continue;
                }
            }
            rt = mid - 1;
        }
        return max;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 자르는 횟수
        int M = Integer.parseInt(st.nextToken()); // 자를 수 있는 지점의 개수
        int L = Integer.parseInt(st.nextToken()); // 롤 케이크의 길이
        int[] cut = new int[M];
        for(int i = 0 ; i < M ; i ++){
            cut[i] = Integer.parseInt(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i ++) sb.append(binarySearch(cut, M, Integer.parseInt(br.readLine()), L) + "\n");
        System.out.println(sb);
    }
}
