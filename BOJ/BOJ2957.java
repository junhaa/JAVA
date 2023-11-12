import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
// BOJ #2957 이진 탐색 트리
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        long sum = 0;
        for(int i = 0 ; i < N ; i ++){
            int cur = Integer.parseInt(br.readLine());
            int dep = Math.max(map.lowerKey(cur) == null ? -1 : map.get(map.lowerKey(cur)), map.higherKey(cur) == null ? -1 : map.get(map.higherKey(cur))) + 1;
            map.put(cur, dep);
            sum += dep;
            sb.append(sum + "\n");
        }
        System.out.println(sb);
    }
}
