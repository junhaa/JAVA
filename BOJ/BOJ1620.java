import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// BOJ #1620 나는야 포켓몬 마스터 이다솜
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<Integer, String> inttochar = new HashMap<>();
		HashMap<String, Integer> chartoint = new HashMap<>();
		for(int i = 1 ; i <= N ; i ++) {
			String name = br.readLine();
			inttochar.put(i, name);
			chartoint.put(name, i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < M ; i ++) {
			String input = br.readLine();
			if(Character.isAlphabetic(input.charAt(0))){
				sb.append(chartoint.get(input) + "\n");
			}
			else {
				sb.append(inttochar.get(Integer.parseInt(input)) + "\n");
			}
		}
		System.out.println(sb);
	}
}
