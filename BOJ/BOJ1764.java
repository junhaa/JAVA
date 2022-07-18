import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// BOJ #1764 µË∫∏¿‚
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Set<String> set = new HashSet<>();
		ArrayList<String> answer = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			set.add(br.readLine());
		}
		for(int i = 0 ; i < M ; i ++) {
			String tmp = br.readLine();
			if(set.contains(tmp)) answer.add(tmp);
		}
		Collections.sort(answer);
		sb.append(answer.size()).append('\n');
		for(String x : answer) {
			sb.append(x).append('\n');
		}
		System.out.println(sb);
	}
}
