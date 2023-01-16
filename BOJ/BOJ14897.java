import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

// BOJ #14897 서로 다른 수와 쿼리 1
class Query implements Comparable<Query>{
	int start, end, sqrtN, num;
	public Query(int start, int end, int sqrtN, int num) {
		this.start = start;
		this.end = end;
		this.sqrtN = sqrtN;
		this.num = num;
	}
	@Override
	public int compareTo(Query o) {
		if(this.start / sqrtN != o.start / sqrtN) return this.start / sqrtN - o.start / sqrtN;
		else return this.end - o.end;
	}
}
public class Main {
	
	static HashMap<Integer, Integer> map = new HashMap<>();
	static int[] arr = new int[1000001], cnt, answer;
	static ArrayList<Query> q = new ArrayList<>();
	static int ansCnt = 0;
	
	private static class FastIO {
		final private int BUFFER_SIZE = 1 << 16;
		final private int INT_SIZE = 10;
		private DataInputStream din;
		private DataOutputStream dout;

		private byte[] inbuffer;
		private int inputbufferpointer, bytesread;
		private byte[] outbuffer;
		private int outputbufferpointer;
		private byte[] bytebuffer;
		
		FastIO() {
			din = new DataInputStream(System.in);
			dout = new DataOutputStream(System.out);
			inputbufferpointer = bytesread = outputbufferpointer = 0;
			bytebuffer = new byte[INT_SIZE];
			inbuffer = new byte[BUFFER_SIZE];
			outbuffer = new byte[BUFFER_SIZE];
			
			
		}
		
		public int nextInt() throws IOException { 
            int ret = 0; 
            byte b = read(); 
            while (b <= ' ') 
                b = read(); 
            boolean isneg = (b == '-'); 
            if (isneg) 
                b = read(); 
            do { 
                ret = ret * 10 + b - '0';
            } while ((b = read()) >= '0' && b <= '9'); 
  
            if (isneg) 
                return -ret; 
            return ret; 
        }
		
		private byte read() throws IOException { 
            if (inputbufferpointer == bytesread) 
                fillbuffer(); 
            return inbuffer[inputbufferpointer++]; 
        }
		
		private void fillbuffer() throws IOException { 
            bytesread = din.read(inbuffer, inputbufferpointer = 0, BUFFER_SIZE); 
            if (bytesread == -1) 
                inbuffer[0] = -1; 
        }
		
		public void write(int i) {
			if (i == 0) {
				writebuffer((byte) '0');
				return;
			}

			if (i < 0) {
				writebuffer((byte) '-');
				i = -i;
			}

			int index = 0;
			while (i > 0) {
				bytebuffer[index++] = (byte) ((i % 10) + '0');
				i /= 10;
			}

			while (index-- > 0)
				writebuffer(bytebuffer[index]);
		}
		
		public void writels(int i) {
			write(i);
			writebuffer((byte)' ');
		}
		
		private void writebuffer(byte b) {
			if (outputbufferpointer == outbuffer.length) {
				flushbuffer();
			}
			outbuffer[outputbufferpointer++] = b;
		}
		
		private void flushbuffer() {
			if (outputbufferpointer != 0) {
				try {
					dout.write(outbuffer, 0, outputbufferpointer);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			outputbufferpointer = 0;
		}		
	}
	
	public void add(int x) {
		if(++cnt[x] == 1) ansCnt ++;
	}
	
	public void delete(int x) {
		if(--cnt[x] == 0) ansCnt --;
	}
	
	public static void main(String[] args) throws IOException {
		Main T = new Main();
		FastIO myIO = new FastIO();
		int N = myIO.nextInt();
		int sqrtN = (int)Math.sqrt(N);
		arr = new int[N];
		int midx = 0;
		for(int i = 0 ; i < N ; i ++) {
			int tmp = myIO.nextInt();
			if(!map.containsKey(tmp)) { 
				map.put(tmp, midx ++);
			}
			arr[i] = map.get(tmp);
		}
		cnt = new int[midx];
		int Q = myIO.nextInt();
		answer = new int[Q];
		for(int i = 0 ; i < Q ; i ++) {
			q.add(new Query(myIO.nextInt() - 1, myIO.nextInt() - 1, sqrtN, i)); // 0 - based
		}
		Collections.sort(q);
		
		int s = q.get(0).start;
		int e = q.get(0).end;
		
		for(int i = s ; i <= e ; i ++) {
			T.add(arr[i]);
		}
		
		answer[q.get(0).num] = ansCnt;
		
		for(int i = 1 ; i < Q ; i ++) {
			Query tmp = q.get(i);
			while(s > tmp.start) T.add(arr[--s]);
			while(e < tmp.end) T.add(arr[++e]);
			while(s < tmp.start) T.delete(arr[s++]);
			while(e > tmp.end) T.delete(arr[e--]);
			
			answer[tmp.num] = ansCnt;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < Q ; i ++) {
			sb.append(answer[i] + "\n");
		}
		System.out.println(sb);
	}
}
