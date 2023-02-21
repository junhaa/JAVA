import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// BOJ #15553 난로
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if(N == 1) { 
			System.out.println(1);
			return;
		}
		int last = Integer.parseInt(br.readLine());
		int first = last;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 1 ; i < N - 1 ; i ++) {
			int tmp = Integer.parseInt(br.readLine());
			list.add(tmp - last - 1);
			last = tmp;
		}
		int lastn = Integer.parseInt(br.readLine());
		list.add(lastn - last - 1);
		long answer = lastn - first + 1;
		
		Collections.sort(list, Collections.reverseOrder());
		
		for(int i = 0 ; i < K - 1 ; i ++) {
			answer -= list.get(i);
		}
		System.out.println(answer);
	}
}
