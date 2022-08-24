import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #2217 ทฮวม
public class Main {
	static ArrayList<Integer> list = new ArrayList<>();
	
	static int solution(int N) {
		int	cnt = 0;
		int max = Integer.MIN_VALUE;
		Collections.sort(list, Collections.reverseOrder());
		for(int i = 0 ; i < N ; i ++) {
			max = Math.max(max, list.get(i) * (i + 1));
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		System.out.println(T.solution(N));
	}
}
