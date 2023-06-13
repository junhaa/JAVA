import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #4158 CD
public class Main {


	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int answer = 0;
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0) break;
			HashSet<Integer> set = new HashSet<>();
			for(int i = 0 ; i < N ; i ++) set.add(Integer.parseInt(br.readLine()));
			for(int i = 0 ; i < M ; i ++) {
				if(set.contains(Integer.parseInt(br.readLine()))) answer ++;
			}
			sb.append(answer + "\n");
		}
		System.out.println(sb);
	}
}
