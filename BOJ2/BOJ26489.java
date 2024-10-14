import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #26489 Gum Gum for Jay Jay
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		int cnt = 0;
		while((input = br.readLine()) != null){
			cnt ++;
		}
		System.out.println(cnt);
	}
}
