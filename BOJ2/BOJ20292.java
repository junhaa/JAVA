import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
// BOJ #20292 컨설팅
public class Main {
	/**
	 * WRITE A TO B 에서 A는 READ, B는 WRITE
	 * READ 에서는 WRITE만 확인
	 * WRITE에서는 READ, WRITE 둘 다 확인
	 * WAIT 호출 후 SET 모두 clear
	 */

	static Set<String> readSet = new HashSet<>(), writeSet = new HashSet<>();

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(true){
			String cur = br.readLine();
			st = new StringTokenizer(cur);
			//write
			if(st.countTokens() == 4){
				st.nextToken();
				String read = st.nextToken();
				st.nextToken();
				String write = st.nextToken();
				if(!main.checkRead(read) || !main.checkWrite(write)){
					main.addWait(read, write, sb);
				}
			}
			else if(st.countTokens() == 2){
				st.nextToken();
				String read = st.nextToken();
				if(!main.checkRead(read)){
					main.addWait(read, null, sb);
				}
			}
			else break;

			sb.append(cur + "\n");
		}
		sb.append("EXIT");
		System.out.println(sb);
	}

	private boolean checkRead(String read){
		if(writeSet.contains(read)){
			return false;
		}
		readSet.add(read);
		return true;
	}

	private boolean checkWrite(String write){
		if(writeSet.contains(write) || readSet.contains(write)){
			return false;
		}
		writeSet.add(write);
		return true;
	}

	private void addWait(String read, String write, StringBuilder sb){
		sb.append("WAIT\n");
		readSet.clear();
		writeSet.clear();
		if(read != null){
			readSet.add(read);
		}
		if(write != null){
			writeSet.add(write);
		}
	}
}
