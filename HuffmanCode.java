import java.util.*;
import java.io.*;

// Xiaoya Huang
// TA: Anthony Tran
// The HuffmanCode class represents a huffman code for given message.
// It keeps track of a binary tree constructed by the huffman algorithm.
public class HuffmanCode {
   private HuffmanNode overallRoot;
   
   // Post: Constructs a HuffmanCode from the given array of frequencies
   //       of ASCII values.
   public HuffmanCode(int[] frequencies){
      Queue<HuffmanNode> counts = new PriorityQueue<HuffmanNode>();
      for (int i = 0; i < frequencies.length; i++){
         if (frequencies[i] > 0){
            counts.add(new HuffmanNode(i, frequencies[i]));
         }
      }
      while (counts.size() > 1){
         HuffmanNode left = counts.remove();
         HuffmanNode right = counts.remove();
         overallRoot = new HuffmanNode(left, right);
         overallRoot.frequency = left.frequency + right.frequency;
         counts.add(overallRoot);
      }
   }
   
   // Pre : The imput is not null and contains data encoded in legal, 
   //       valid standard format
   // Post: Constructs a HuffmanCode with the constructed code from file.
   //       The file is read by the given Scanner input.
   public HuffmanCode(Scanner input){
      while(input.hasNextLine()){
         int character = Integer.parseInt(input.nextLine());
         String code = input.nextLine();
         overallRoot = addCode(overallRoot, code, character);
      }
   }
   
   // Post: It is a helper method for HuffmanCode construction.
   //       The binary code used to represent the ASCII value and
   //       the ASCII value is used to form a HuffmanNode
   //       The HuffmanNode curr is the currently formed huffman tree during construction 
   //       Return current huffman tree 
   private HuffmanNode addCode(HuffmanNode curr, String code, int character){
      if (code.length() == 0){
         curr = new HuffmanNode(character, 0);
      } else {
         if (curr == null){
            curr = new HuffmanNode(0, 0);
         }
         if (code.charAt(0) == '0'){
            curr.leftNode = addCode(curr.leftNode, code.substring(1), character);
         } else {
            curr.rightNode = addCode(curr.rightNode, code.substring(1), character);
         }
      }
      return curr;
   }
   
   // Post: It stores the current huffman codes to the given output stream 
   //       in the standard format
   public void save(PrintStream output){
      String code = "";
      save(overallRoot, output, code);  
   }
   
   // Post: Output the given curr Huffman tree to an file 
   //       by the given PrintStream. The given String code is the 
   //       current Huffman code of the current node.
   //       The output file should be writtern in the standard form.
   private void save(HuffmanNode curr, PrintStream output, String code){
      if (curr.rightNode == null && curr.leftNode == null){
         output.println(curr.character);
         output.println(code);
      } else {
         save(curr.leftNode, output, code + "0");
         save(curr.rightNode, output, code + "1");  
      }
   }
   
   // Post: It reads individual bits from the given BitInputStream input and 
   //       write the corresponding characters to the given PrintStream output. 
   //       It should stop reading when the given BitInputStream input is empty.
   //       Assum the input contains a legal encoding of characters  
   public void translate(BitInputStream input, PrintStream output){
      while(input.hasNextBit()){
         translate(input, output, overallRoot);
      }
   }
   
   // Post: It reads the bits form the given BitInputStream input
   //       If the input has no next bit, it print out the character value of 
   //       the current HuffmanNode called curr by the given PrintStream output
   //       Otherwise, it will read the next bit and update the curr Huffman node 
   //       Assume that the input contains a legal encoding of characters 
   private void translate(BitInputStream input, PrintStream output, HuffmanNode curr){
      if (curr.leftNode == null && curr.rightNode == null){
         output.write(curr.character);
      } else {
         int bit = input.nextBit();
         if (bit == 1){
            translate(input, output, curr.rightNode);
         } else {
            translate(input, output, curr.leftNode);
         }
      }
   }
}