import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

// BOJ #25192 인사성 밝은 곰곰이
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashSet<String> set = new HashSet<>();
		int answer = 0;
		for(int i = 0 ; i < N ; i ++) {
			String tmp = br.readLine();
			if(tmp.equals("ENTER")) { 
				set = new HashSet<>();
				continue;
			}
			if(!set.contains(tmp)) {
				set.add(tmp);
				answer ++;
			}
		}
		System.out.println(answer);
	}
}
