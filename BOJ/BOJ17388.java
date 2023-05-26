import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17388 와글와글 숭고한
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int min = Integer.MAX_VALUE;
		int minUniv = -1;
		if(s < min) {
			minUniv = 1;
			min = s;
		}
		if(g < min) {
			minUniv = 2;
			min = g;
		}
		if(h < min) {
			minUniv = 3;
			min = h;
		}
		if(s + g + h >= 100) {
			System.out.println("OK");
		}
		else {
			if(minUniv == 1) {
				System.out.println("Soongsil");
			}
			else if(minUniv == 2) {
				System.out.println("Korea");
			}
			else if(minUniv == 3) {
				System.out.println("Hanyang");
			}
			else {
				System.out.println("error");
			}
		}
	}
}
