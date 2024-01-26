import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BOJ #25379 피하자
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		double evenSum = 0;
		double oddSum = 0;
		Queue<Integer> eq = new LinkedList<>();
		Queue<Integer> oq = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if (cur % 2 == 0) {
				eq.offer(i + 1);
				evenSum += i + 1;
			} else {
				oq.offer(i + 1);
				oddSum += i + 1;
			}
		}

		Queue<Integer> fq = null;
		if (evenSum / eq.size() >= ((double)N + 1) / 2) {
			fq = oq;
		} else
			fq = eq;

		int idx = 1;
		long answer = 0;
		while (!fq.isEmpty()) {
			int nidx = fq.poll();
			answer += nidx - idx;
			idx++;
		}
		System.out.println(answer);
	}
}
