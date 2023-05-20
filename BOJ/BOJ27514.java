import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #27514 1차원 2048
public class Main {
	
	static int[] cnt = new int[63];
	
	public static int log2nlz( long val )
	{
	    if( val == 0 )
	        return 0;
	    return 63 - Long.numberOfLeadingZeros( val );
	}
	
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = -1;
		for(int i = 0 ; i < N ; i ++) {
			Long tmp = Long.parseLong(st.nextToken());
			if(tmp == 0) continue;
			cnt[log2nlz(tmp)] ++;
		}
		for(int i = 0 ; i < 62 ; i ++) {
			cnt[i + 1] += cnt[i] / 2;
		}
		for(int i = 62 ; i >= 0 ; i --) {
			if(cnt[i] > 0) {
				max = i;
				break;
			}
		}
		if(max == -1) System.out.println(0);
		else System.out.println((long)Math.pow(2, max));
	}
}

