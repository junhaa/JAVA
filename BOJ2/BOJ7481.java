import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #7481 ATM 놀이
public class Main {

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(T -- > 0){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			sb.append(main.solve(a, b, s)).append("\n");
		}
		System.out.println(sb);
	}

	private String solve(int a, int b, int s){
		boolean swap = false;
		if(a > b){
			swap = true;
			int tmp = a;
			a = b;
			b = tmp;
		}

		boolean[] check = new boolean[b];

		int cb = s / b;
		s %= b;
		int ca = s / a;
		s %= a;

		if(s == 0){
			return swap ? cb + " " + ca : ca + " " + cb;
		}

		check[s] = true;
		while(true){
			cb --;
			s += b;
			if(cb < 0) return "Impossible";
			ca += s / a;
			s %= a;
			if(s == 0){
				return swap ? cb + " " + ca : ca + " " + cb;
			}
			else if(check[s]){
				return "Impossible";
			}
			else{
				check[s] = true;
			}
		}


	}

}
