import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ #26122 가장 긴 막대 자석
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		String input = br.readLine();
		int max = Integer.MIN_VALUE;
		int nMax = 0;
		int sMax = 0;
		boolean lastN = false;

		for (int i = 0; i < K; i++) {
			char cur = input.charAt(i);
			// 문자가 바뀌는 경우
			if ((cur == 'N' && !lastN) || (cur == 'S' && lastN)) {
				max = Math.max(Math.min(nMax, sMax), max);
				if (lastN) {
					sMax = 1;
				} else {
					nMax = 1;
				}
				lastN = !lastN;
			} else {
				if (lastN) {
					nMax++;
				} else
					sMax++;
			}
		}
		max = Math.max(Math.min(nMax, sMax), max);
		System.out.println(max * 2);
	}
}
