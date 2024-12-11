import java.io.*;

public class HuffmanNode implements Comparable<HuffmanNode>, Serializable {
    public int freq;
    public byte value;
    public HuffmanNode left, right;

    public HuffmanNode(int freq, byte value) {
        this.freq = freq;
        this.value = value;
        this.left = null;
        this.right = null;
    }
 
    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
        this.value = -1;
    }
    
    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.freq, other.freq);
    }
	
	public HuffmanNode getLeftChild() {
		return left;
	}
	
	public HuffmanNode getRightChild() {
		return right;
	}
	
	public byte getValue() {
		return value;
	}
}