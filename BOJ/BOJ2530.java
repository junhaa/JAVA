import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2530 인공지능 시계
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(br.readLine());
		int sum = (A * 3600) + (B * 60) + C + D;
		int hour = sum / 3600 % 24;
		sum %= 3600;
		int min = sum / 60 ;
		sum %= 60;
		System.out.println(hour + " " + min + " " + sum);
	}
}
