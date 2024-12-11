import java.io.*;
import java.util.*;

public class Huff implements Serializable {
	
	public static byte[] puff;
	public static File inputFile;
	public static File outputFile;
	public static Map<Byte, Integer> frequencies;
	public static int originalFileLength;
	public static String encodedString;
	public static HuffmanNode root;
	
    public static void buildCodeMap(HuffmanNode node, String code, Map<Byte, String> codeMap) {
        if (node.isLeaf()) {
            codeMap.put(node.value, code);
        } else {
            buildCodeMap(node.left, code + "0", codeMap);
            buildCodeMap(node.right, code + "1", codeMap);
        }
	}
		

	public static Map<Byte, Integer> getFrequencyMap(byte[] fileBytes) {
		Map<Byte, Integer> freqMap = new HashMap<>();
		for (byte b : fileBytes) {
			if (freqMap.containsKey(b)) {
				freqMap.put(b, freqMap.get(b) + 1);
			}
			else freqMap.put(b, 1);
		}
		return freqMap;
	}


	public static HashMap<Byte, String> generateHuffmanCodes(HuffmanNode root) {
		HashMap<Byte, String> codes = new HashMap<>();
		buildCodeMap(root, "", codes);
		return codes;
	}

	public static String encodedByteString(byte[] bytes, HuffmanNode root) {
		HashMap<Byte, String> codes = generateHuffmanCodes(root);
		StringBuilder encoded = new StringBuilder();
		for (byte b : bytes) {
			encoded.append(codes.get(b));
		}
		return encoded.toString();
	}

	public static String padBinaryString(String binaryString) {
		int length = binaryString.length();
		int paddingLength = (8 - length % 8) % 8;
		StringBuilder padded = new StringBuilder(binaryString);
		for (int i = 0; i < paddingLength; i++) {
			padded.append('0');
		}
		return padded.toString();
	}
	
	public static byte[] binaryStringToByteArray(String binaryString) {
		int strLen = binaryString.length();
		byte[] byteArray = new byte[strLen / 8];
		for (int i = 0; i < byteArray.length; i++) {
			String byteStr = binaryString.substring(i * 8, (i + 1) * 8);
			byteArray[i] = (byte) Integer.parseInt(byteStr, 2);
		}
		return byteArray;
	}

	public static byte[] encode(byte[] bytes, HuffmanNode root) {
		HashMap<Byte, String> codes = generateHuffmanCodes(root);
		StringBuilder encoded = new StringBuilder();
		for (byte b : bytes) {
			encoded.append(codes.get(b));
		}
		String encodedString = encoded.toString();
		encodedString = padBinaryString(encodedString);
		int len = (encodedString.length() + 7) / 8;
		byte[] encodedBytes = new byte[len];
		for (int i = 0; i < len; i++) {
			int start = i * 8;
			int end = Math.min(start + 8, encodedString.length());
			String byteString = encodedString.substring(start, end);
			encodedBytes[i] = (byte) Integer.parseInt(byteString, 2);
		}
		return encodedBytes;
	}

	public static byte[] decode(String encoded, HuffmanNode root) {
		List<Byte> decodedBytes = new ArrayList<>();
		HuffmanNode current = root;

		for (int i = 0; i < encoded.length(); i++) {
			char c = encoded.charAt(i);
			if (c == '0') {
				current = current.getLeftChild();
			}
			else if (c == '1') {
				current = current.getRightChild();
			}

			if (current.isLeaf()) {
				decodedBytes.add(current.getValue());
				current = root;
			}
		}

		byte[] result = new byte[decodedBytes.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = decodedBytes.get(i);
		}
		return result;
	}
		
	
		
	public static void saveTreeToFile(HuffmanNode root, String bitString, File file) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(root);
		out.writeObject(bitString);
		out.close();
	}

	
		
		
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Use: java Huff <input.txt> <output.bin>");
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

        byte[] fileBytes = new byte[(int) inputFile.length()];
		
        try {
			FileInputStream in = new FileInputStream(inputFile);
            in.read(fileBytes);
        }
		catch (IOException e) {
            e.printStackTrace();
        }
		
		
		// for (int i = 0; i < fileBytes.length; i++) {
			// System.out.println(fileBytes[i]);
		// }
        
		
		frequencies = getFrequencyMap(fileBytes);
		
		// System.out.println(frequencies);
		
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : frequencies.entrySet()) {
            pq.offer(new HuffmanNode(entry.getValue(), entry.getKey()));
        }
        
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.freq + right.freq, left, right);
            pq.offer(parent);
        }
        root = pq.poll();
        
        Map<Byte, String> codeMap = new HashMap<>();
        buildCodeMap(root, "", codeMap);
		
        for (Map.Entry<Byte, String> entry : codeMap.entrySet()) {
            System.out.println("Byte: " + entry.getKey() + ", Code: " + entry.getValue());
        }
		
		
		encodedString = encodedByteString(fileBytes, root);
		System.out.println("Unpadded String: " + encodedString);
		System.out.println("Padded String Divisble by 8: " + padBinaryString(encodedString));
		
		byte[] encodedBytes = binaryStringToByteArray(padBinaryString(encodedString));
		System.out.print("Encoded byte array: [");
		for (int j = 0; j < encodedBytes.length; j++) {
			if (j != encodedBytes.length - 1) System.out.print(encodedBytes[j] + ", ");
			else System.out.println(encodedBytes[j] + "]");
		}
		
		
		try {
			FileOutputStream out = new FileOutputStream(outputFile);
			out.write(encodedBytes);
			saveTreeToFile(root, padBinaryString(encodedString), outputFile);
			out.close();
			System.out.println(inputFile.toString() + " was successfully huffed to " + outputFile.toString() + "!");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// puff = decode(padBinaryString(encodedString), root);
		
		// for (int i = 0; i < puff.length; i++) {
			// System.out.println(puff[i]);
		// }
		
    }
    
}