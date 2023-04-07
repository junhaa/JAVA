import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #2980 도로와 신호등
public class Main {

	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int curD = 0;
		int curT = 0;
		for(int i = 0 ; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			if(D >= L) break;
			int R = Integer.parseInt(st.nextToken());
			int G = Integer.parseInt(st.nextToken());
			curT += D - curD;
			curD = D;
			double tmp = (double)curT / (R + G);
			if(tmp - (int)tmp < (double)R / (R + G)) { // 신호등에 멈춘경우
				curT = (int)((tmp * (R + G)) + (((double)R / (R + G) - (tmp - (int)tmp)) * (R + G)));
			}
		}
		if(curD < L) {
			curT += L - curD;
			curD = L;
		}
		System.out.println(curT);
	}	
}	
