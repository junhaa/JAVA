import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// BOJ #11656 접미사 배열
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		ArrayList<String> list = new ArrayList<>();
		list.add(S);
		for(int i = 1 ; i < S.length() ; i ++) {
			list.add(S.substring(i));
		}
		Collections.sort(list);
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < list.size() ; i ++) {
			sb.append(list.get(i) + "\n");
		}
		System.out.println(sb);
	}
}
