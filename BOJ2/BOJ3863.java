import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// BOJ #3863 행복한 전화 통화
class Call{
    int start, end;

    public Call(int start, int duration) {
        this.start = start;
        this.end = start + duration;
    }

    public boolean isInRange(int startRange, int endRange){
        if(end <= startRange || start >= endRange) return false;
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M  = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            List<Call> calls = new ArrayList<>();
            for(int i = 0 ; i < N ; i ++){
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                st.nextToken();
                calls.add(new Call(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            for(int i = 0 ; i < M ; i ++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = start + Integer.parseInt(st.nextToken());
                sb.append(getInRangeCalls(start, end, calls)).append("\n");
            }

        }

        System.out.println(sb);
    }

    private static long getInRangeCalls(int start, int end, List<Call> calls){
        long count = 0;
        for (Call call : calls) {
            if(call.isInRange(start, end)) count ++;
        }
        return count;
    }
}
