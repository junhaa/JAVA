import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #25194 결전의 금요일
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] tasks = new int[7];

        for(int i = 0 ; i < N ; i ++){
            tasks[Integer.parseInt(st.nextToken()) % 7] ++;
        }

        System.out.println(new Main().canGoToGym(0, tasks) ? "YES" : "NO");
    }

    private boolean canGoToGym(int curSum, int[] tasks) {
        if(curSum % 7 == 4){
            return true;
        }
        for(int i = 1 ; i <= 6 ; i ++) {
            if(tasks[i] > 0) {
                tasks[i] --;
                if(canGoToGym(curSum + i, tasks)) {
                    return true;
                }
                tasks[i] ++;
            }
        }
        return false;
    }
}
