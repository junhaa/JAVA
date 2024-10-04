import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

// BOJ #12033 김인천씨의 식료품가게 (Small)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int j = 1 ; j <= T ; j ++){
			Stack stack = new Stack();
			sb.append("Case #" + j + ":");
			int N = Integer.parseInt(br.readLine());
			List<Integer> list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			Map<Integer, Integer> map = new HashMap<>();
			for(int i = 0 ; i < 2 * N ; i ++){
				int cur = Integer.parseInt(st.nextToken());
				list.add(cur);
				map.put(cur, map.getOrDefault(cur, 0) + 1);
			}
			for(int i = list.size() - 1 ; i >= 0 ; i --){
				int cur = list.get(i);
				if(!map.containsKey(cur)) continue;
				if(map.get(cur) == 1){
					map.remove(cur);
				}
				else{
					map.put(cur, map.get(cur) - 1);
				}
				int original = cur / 4 * 3;
				if(map.containsKey(original)){
					stack.push(original);
					if(map.get(original) == 1) {
						map.remove(original);
					}
					else{
						map.put(original, map.get(original) - 1);
					}
				}
			}

			int len = stack.size();
			for(int i = 0 ; i < len ; i ++){
				sb.append(" " + stack.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
