import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// BOJ #5525 IOIOI
public class Main {
	static int curlen = 0;
	static Map<Integer, Integer> map = new HashMap<>();

	static void addMap() {
		if (curlen % 2 == 0) {
			curlen--;
		}
		map.put(curlen / 2, map.getOrDefault(curlen / 2, 0) + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int len = Integer.parseInt(br.readLine());
		String input = br.readLine();
		boolean flag = false;

		char last = ' ';
		for (int i = 0; i < len; i++) {
			char cur = input.charAt(i);
			if (cur == 'I') {
				if (!flag) {
					flag = true;
					curlen++;
				} else {
					if (last == 'O') {
						curlen++;
					} else {
						if (curlen >= 3) {
							addMap();
						}
						curlen = 1;
					}
				}
			} else { // 'O'
				if (flag) {
					if (last == 'I') {
						curlen++;
					} else {
						if (curlen >= 3) {
							addMap();
						}
						flag = false;
						curlen = 0;
					}
				}
			}
			last = cur;
		}
		if (curlen >= 3)
			addMap();

		int answer = 0;
		for (int s : map.keySet()) {
			if (s < N)
				continue;
			answer += (s - (N - 1)) * map.get(s);
		}
		System.out.println(answer);
	}
}
