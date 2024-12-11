import java.io.*;

public class Caesar {
	
	public static void main(String[] args) {
		int key;
		File infile;
		File outfile;
		
		try {
			key = Integer.parseInt(args[0]);
		}
		catch(Exception e) {
			System.out.println("Use: java Caesar <key> <infile> <outfile>"); return;
		}
		
		try {
			infile = new File(args[1]);
			BufferedReader in = new BufferedReader(new FileReader(infile));
			//String line;
			//while ((line = in.readLine()) != null) {
			//System.out.println(line);
			//}
			PrintWriter out;
			if (args.length == 3) {
				outfile = new File(args[2]);
				out = new PrintWriter(new FileWriter(outfile));
			} else {
				out = new PrintWriter(System.out);
			}
			
			int charValue;
			while ((charValue = in.read()) != -1) {
				char outputChar;
				if (charValue == '\n') {
					out.write('\n');
					//continue;
				}
				if (charValue == '\t') {
					out.write('\t');
					//continue;
				}
				if (charValue < 32 || charValue > 126) outputChar = (char)charValue;
				else {
					charValue = charValue + key;
					if (charValue > 126) charValue = charValue - 95;
					if (charValue < 32) charValue = charValue + 95;
					outputChar = (char)charValue;
				}
				out.write(outputChar);
			}
			in.close();
			out.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}