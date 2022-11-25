import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #5800 성적 통계
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int k = 1 ; k <= K ; k ++) {
			st = new StringTokenizer(br.readLine());
			sb.append("Class " + k + "\n");
			int N = Integer.parseInt(st.nextToken());
			int gap = Integer.MIN_VALUE;
			ArrayList<Integer> list = new ArrayList<>();
			for(int i = 0 ; i < N ; i ++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			Collections.sort(list, Collections.reverseOrder());
			for(int i = 0 ; i < list.size() - 1 ; i ++) {
				gap = Math.max(gap, list.get(i) - list.get(i + 1));
			}
			sb.append("Max " + list.get(0) + ", " + "Min " + list.get(list.size() - 1) + ", " + "Largest gap " + gap + "\n");
		}
		System.out.println(sb);
	}
}
