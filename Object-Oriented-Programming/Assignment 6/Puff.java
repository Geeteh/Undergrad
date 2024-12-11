import java.io.*;

public class Puff implements Serializable {
	
	static File inputFile;
	static File outputFile;
	static byte[] decodedBytes;
	static byte[] encodedBytes;
	static HuffmanNode root;
	static String bitString;
	
	public static Object readTreeFromFile(File file) throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		HuffmanNode root = (HuffmanNode) in.readObject();
		String bitString = (String) in.readObject();
		byte[] bytes = new byte[in.available()];
		in.readFully(bytes);
		in.close();
		return new Object[]{root, bitString, bytes};
	}
	

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		if (args.length < 2) {
			System.out.println("Use: java Puff <input.txt> <output.bin>");
			System.exit(1);
		}
        
		try {
			inputFile = new File(args[0]);
			outputFile = new File(args[1]);
		}
		catch (Exception e) {
			System.out.println("File was not read or does not exist");
			System.exit(1);
		}
		
		try {
			Object[] result = (Object[]) readTreeFromFile(inputFile);
			root = (HuffmanNode)result[0];
			bitString = (String)result[1];
			encodedBytes = (byte[])result[2];
			System.out.println("Loaded bit string: " + bitString);
			System.out.println("Loaded HuffmanNode root: " + root);
		}
		catch (IOException e) {
			System.out.println("File was not Huffed to bytes properly or does not exist");
			e.printStackTrace();
		}
	
		
		decodedBytes = Huff.decode(bitString, root);
		
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
			outputStream.write(decodedBytes);
			System.out.println(inputFile.toString() + " was successfully puffed to " + outputFile.toString() + "!");
		}
		catch (IOException e) {
			System.out.println("Trouble puffing");
			System.exit(1);
		}
		
		System.out.print("Decoded byte array: [");
		for (int j = 0; j < decodedBytes.length; j++) {
			if (j != decodedBytes.length - 1) System.out.print(decodedBytes[j] + ", ");
			else System.out.println(decodedBytes[j] + "]");
		}
	}
}