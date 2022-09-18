import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #14719 ºø¹°
public class Main {

	static int[] arr;
	
	static int solution(int H, int W) {
		int answer = 0;
		for(int i = 1 ; i <= H ; i ++) {
			int lt = -1;
			boolean getL = false;
			for(int k = 0 ; k < W ; k ++) {
				if(arr[k] >= i) { // blank X
					if(getL) {
						getL = false;
						answer += k - lt - 1;
					}
					if(!getL && k + 1 < W && arr[k + 1] < i) { // lt ±¸ÇÏÁö X  ´ÙÀ½ Ä­ÀÌ ºóÄ­ÀÌ¸é
						lt = k;
						getL = true;
					}
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		arr = new int[W];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < W ; i ++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(T.solution(H, W));
	}
}
