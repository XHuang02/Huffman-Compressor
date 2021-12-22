import java.util.*;

// Xiaoya Huang
// TA: Anthony Tran
// HuffmanNode class is used to create single nodes.
// They store the ASCII value of a character and the frequency of it appearing
// in the given text, or connect to two other HuffmanNodes.
public class HuffmanNode implements Comparable<HuffmanNode>{
    public int frequency;
    public int character;
    public HuffmanNode leftNode;
    public HuffmanNode rightNode;

    // Construct a node with the given ASCII value of the character called
    // character, the given frequency of the character and the null link
    public HuffmanNode(int character, int frequency) {
        this.character = character;
        this.frequency = frequency;

    }
    // Construct a node with null value in character and frequency but links 
    // to other nodes.
    public HuffmanNode(HuffmanNode leftNode, HuffmanNode rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    // Determine the priority between two HuffmanNode.
    // The one with lower frequncy will have higher priority.
    public int compareTo (HuffmanNode other){
      return this.frequency - other.frequency;
    }
}
