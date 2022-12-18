import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #2012 등수 매기기
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long answer = 0;
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(list);
		int idx = 1;
		for(int i = 0 ; i < N ; i ++) {
			answer += Math.abs(list.get(i) - idx ++);
		}
		System.out.println(answer);
	}
}
