import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// BOJ #18113 그르다 김가놈
public class Main {
    static int N, K, M;

    static int binarySearch(ArrayList<Integer> list, int startMax){
        int lt = 1;
        int rt = startMax;
        int max = Integer.MIN_VALUE;
        while(lt <= rt){
            int mid = (lt + rt) / 2;
            int cnt = 0;
            for(int cur : list){
                cnt += cur / mid;
            }
            if(cnt >= M){
                lt = mid + 1;
                max = mid;
            }
            else{
                rt = mid - 1;
            }
        }
        if(max == Integer.MIN_VALUE){
            return -1;
        }
        return max;
    }

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < N ; i ++){
            int tmp = Integer.parseInt(br.readLine());
            if(tmp >= 2 * K) tmp -= 2 * K;
            else if(tmp > K) tmp -= K;
            else continue;
            max = Math.max(tmp, max);
            list.add(tmp);
        }
        System.out.println(binarySearch(list, max));
    }
}
