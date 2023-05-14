import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// BOJ #25757 임스와 함께하는 미니게임
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashSet<String> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int cnt = 0;
		char game = st.nextToken().charAt(0);
		if(game == 'Y') {
			cnt = 1;
		}
		else if(game == 'F') cnt = 2;
		else cnt = 3;
		for(int i = 0 ; i < N ; i ++) {
			set.add(br.readLine());
		}
		System.out.println(set.size() / cnt);
	}
}
