import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #10162 전자레인지
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = 300;
		int b = 60;
		int c = 10;
		int ac = 0, bc = 0, cc = 0;
		int time = Integer.parseInt(br.readLine());
		ac += time / a;
		time %= a;
		bc += time / b;
		time %= b;
		if(time % c != 0) {
			System.out.println(-1);
			return;
		}
		cc += time / c;
		System.out.println(ac + " " + bc + " " + cc);
	}
}
