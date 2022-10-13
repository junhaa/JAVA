import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2525 오븐 시계
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int hour = Integer.parseInt(st.nextToken());
		int min = Integer.parseInt(st.nextToken());
		min += Integer.parseInt(br.readLine());
		hour += min / 60;
		min %= 60;
		hour %= 24;
		System.out.println(hour + " " + min);
	}
	
}
