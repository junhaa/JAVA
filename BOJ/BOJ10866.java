import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// BOJ #10866 덱
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deque = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String query = st.nextToken();
			switch (query) {
				case "push_front":
					deque.offerFirst(Integer.parseInt(st.nextToken()));
					break;

				case "push_back":
					deque.offerLast(Integer.parseInt(st.nextToken()));
					break;

				case "pop_front":
					if (deque.isEmpty()) {
						sb.append("-1");
					} else {
						sb.append(deque.pollFirst());
					}
					sb.append("\n");
					break;

				case "pop_back":
					if (deque.isEmpty()) {
						sb.append("-1");
					} else {
						sb.append(deque.pollLast());
					}
					sb.append("\n");
					break;

				case "size":
					sb.append(deque.size());
					sb.append("\n");
					break;

				case "empty":
					if (deque.isEmpty()) {
						sb.append(1);
					} else {
						sb.append(0);
					}
					sb.append("\n");
					break;

				case "front":
					if (deque.isEmpty()) {
						sb.append(-1);
					} else {
						sb.append(deque.peekFirst());
					}
					sb.append("\n");
					break;

				case "back":
					if (deque.isEmpty()) {
						sb.append(-1);
					} else {
						sb.append(deque.peekLast());
					}
					sb.append("\n");
					break;
			}
		}
		System.out.println(sb);
	}
}
