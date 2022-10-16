import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #5532 방학 숙제
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(br.readLine());
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		int D = Integer.parseInt(br.readLine());
		int bb = B / D;
		if(B % D != 0) bb ++;
		int aa = A / C;
		if(A % C != 0) aa ++;
		System.out.println(L - Math.max(aa, bb));
	}
}

