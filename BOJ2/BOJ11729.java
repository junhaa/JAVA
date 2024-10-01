import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #11729 하노이 탑 이동 순서
public class Main {
	static int K = 0 ;
	static StringBuilder sb = new StringBuilder();

	private void recursive(int main, int target, int tmp, int curN){
		if(curN == 1){
			K ++;
			sb.append(main + " " + target + "\n");
			return;
		}

		recursive(main, tmp, target, curN - 1);
		K ++;
		sb.append(main + " " + target + "\n");
		recursive(tmp, target, main, curN - 1);
	}
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		main.recursive(1,3, 2, N);
		System.out.println(K + "\n" + sb);
	}
}
