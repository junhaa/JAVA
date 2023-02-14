import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #15961 회전 초밥
public class Main {

	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0 ; i < k ; i ++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		int max = map.size();
		if(!map.containsKey(c)) max ++;
		for(int lt = 0 ; lt < N ; lt ++) {
			map.put(arr[lt], map.get(arr[lt]) - 1);
			if(map.get(arr[lt]) == 0) map.remove(arr[lt]);
			map.put(arr[(lt + k) % N], map.getOrDefault(arr[(lt + k) % N], 0) + 1);
			int tmp = map.size();
			if(!map.containsKey(c)) tmp ++;
			max = Math.max(tmp, max);
		}
		System.out.println(max);
	}
}
