import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// BOJ #19940 피자 오븐
public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException  {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while(T -- > 0){
			int N = Integer.parseInt(br.readLine());
			main.solve(N);
		}
		System.out.println(sb);
	}

	private void solve(int N){
		int addh = 0, addt = 0, mint = 0, addo = 0, mino = 0;

		addh += N / 60;
		N %= 60;

		if(N > 35){
			addh ++;
			mint += 6 - N / 10;
			N %= 10;
		}
		else{
			addt += N / 10;
			N %= 10;
		}

		if(N > 5){
			addt ++;
			mino = 10 - N;
		}
		else if(N == 5 && mint >= 1){
			mint --;
			mino = 10 - N;
		}
		else{
			addo += N;
		}

		if(addt > mint){
			addt -= mint;
			mint = 0;
		}
		else{
			mint -= addt;
			addt = 0;
		}

		sb.append(addh + " " + addt + " " + mint + " " + addo + " " + mino + "\n");
	}
}
