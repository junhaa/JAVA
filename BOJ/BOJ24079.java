import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #24079 移動 (Moving)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(
			Integer.parseInt(br.readLine()) + Integer.parseInt(br.readLine()) <= Integer.parseInt(br.readLine()) ? 1 :
				0);
	}
}
