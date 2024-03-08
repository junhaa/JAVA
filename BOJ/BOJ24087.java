import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #24087 アイスクリーム (Ice Cream)
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(br.readLine());
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		if (s <= a) {
			System.out.println(250);
			return;
		}
		System.out.println((int)Math.ceil(((double)s - a) / b) * 100 + 250);
	}
}
