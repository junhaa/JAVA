import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #1085 직사각형에서 탈출
public class Main {
	public static int solution(int x, int y, int w, int h) {
		int min = Integer.MAX_VALUE;
		min = Math.min(min, x);
		min = Math.min(min, y);
		min = Math.min(min, w - x);
		min = Math.min(min, h - y);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		System.out.println(T.solution(x,y,w,h));
	}
}
