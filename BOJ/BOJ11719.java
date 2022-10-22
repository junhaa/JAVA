import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11719 그대로 출력하기
public class Main {

	public static void main(String[] args) throws IOException{
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input;
		while((input = br.readLine()) != null) {
			sb.append(input).append("\n");
		}
		System.out.println(sb);
	}
}
