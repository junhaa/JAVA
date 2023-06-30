import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ # 10709 기상캐스터
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < H ; i ++) {
			int cur = -1;
			String input = br.readLine();
			for(int j = 0 ; j < W ; j ++) {
				char tmp = input.charAt(j);
				if(tmp == 'c') {
					cur = 0;
				}
				else if(cur != -1){
					cur ++;
				}
				sb.append(cur + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
