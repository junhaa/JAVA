import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11721 열 개씩 끊어 출력하기
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int idx = 0;
		StringBuilder sb = new StringBuilder();
		while(idx < input.length()) {
			sb.append(input.charAt(idx));
			idx ++;
			if(idx % 10 == 0) sb.append("\n");
		}
		System.out.println(sb);
	}
}
