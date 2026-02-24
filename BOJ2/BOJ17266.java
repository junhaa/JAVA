import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// BOJ #17266 어두운 굴다리
public class Main {
    public static void main(String[] args) throws IOException {
        Main T = new Main();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Integer> x = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        System.out.println(T.solve(N, x));
    }

    public int solve(int N, List<Integer> x){
        int l = Math.max(x.get(0), N - x.get(x.size() - 1));
        int r = N;
        int height = (l + r) / 2;
        int answer = Integer.MAX_VALUE;

        while(l <= r) {
            height = (l + r) / 2;

            if(fillRange(x, height)){
                r = height - 1;
                answer = Math.min(answer, height);
                continue;
            }
            l = height + 1;
        }
        return answer;
    }

    private boolean fillRange(List<Integer> x, int height) {
        int cur = x.get(0);
        for(int i = 1 ; i < x.size() ; i ++){
            if(height * 2 < x.get(i) - cur) {
                return false;
            }
            cur = x.get(i);
        }
        return true;
    }
}
