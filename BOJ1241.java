import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

// BOJ #1241 ∏”∏Æ ≈Â≈Â
public class Main {
	static int[] arr;
	static int[] ch;

	public void solution(int N) {
		int[] answer = new int[N];
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i = 0 ; i < N ; i ++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		for(int i = 0 ; i < N ; i ++) {
			if(ch[arr[i]] != 0) continue;
			if(arr[i] == 1) {
				ch[arr[i]] = map.get(1);
			}
			else {
				for(int k = 1 ; k <= (int)Math.sqrt(arr[i]) ; k ++) {
					if(arr[i] % k == 0) {
						if(k == arr[i] / k) {
							ch[arr[i]] += map.getOrDefault(k, 0);
						}
						else {
							ch[arr[i]] += map.getOrDefault(k, 0);
							ch[arr[i]] += map.getOrDefault(arr[i] / k, 0);
						}
					}
				}	
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int max = Integer.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			arr[i] = tmp;
			if(max < tmp) max = tmp;
		}
		ch = new int[max + 1];
		T.solution(N);
		for(int i = 0 ; i < N ; i ++) {
			System.out.println(ch[arr[i]] - 1);
		}
	}
}
