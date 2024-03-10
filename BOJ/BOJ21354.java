import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #21354 Äpplen och päron
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		if (a * 7 < b * 13) {
			System.out.println("Petra");
		} else if (a * 7 > b * 13) {
			System.out.println("Axel");
		} else {
			System.out.println("lika");
		}
	}
}
