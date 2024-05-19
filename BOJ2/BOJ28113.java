import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #28113 정보섬의 대중교통
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		if (a < b) {
			System.out.println("Bus");
		} else if (b < a) {
			System.out.println("Subway");
		} else {
			System.out.println("Anything");
		}
	}
}
