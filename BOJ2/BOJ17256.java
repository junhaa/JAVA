import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #17256 달달함이 넘쳐흘러 
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ax = Integer.parseInt(st.nextToken());
		int ay = Integer.parseInt(st.nextToken());
		int az = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int zx = Integer.parseInt(st.nextToken());
		int zy = Integer.parseInt(st.nextToken());
		int zz = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		sb.append((zx - az) + " ");
		sb.append((zy / ay) + " ");
		sb.append((zz - ax) + " ");
		System.out.println(sb);
	}
}
