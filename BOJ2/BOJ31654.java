import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ #31654 Adding Trouble
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		if(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) == Integer.parseInt(st.nextToken())){
			System.out.println("correct!");
		}
		else{
			System.out.println("wrong!");
		}
	}

}
