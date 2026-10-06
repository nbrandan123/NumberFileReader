package newsom;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LinkedList {
	// MEMBERS
	private Node head;
	private int size;
	
	// CONSTRUCTOR
	public LinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	// GETTER
	public int getSize() {
		return size;
	}



	// FUNCTIONS
	// 1. Adding a new node
	public void addNode(int num) {
		Node newNode = new Node(num);
		if (head == null) {
			head = newNode;
		}
		else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
		}
		size++;
	}
	
	// 2. Display the linked list
	public void displayList() {
		Node current = head;
		while (current != null) {
			System.out.print(current.num + " ");
			current = current.next;
		}
		System.out.println();
	}
	
	// 3. Take contents of file and turn into a linked list
	public static LinkedList toList(String contents) {
		String[] numbers = contents.split("\\s+");
		LinkedList validList = new LinkedList();
		
		for (String number : numbers) {
			try {
				int num = Integer.parseInt(number);
				validList.addNode(num);
			}
			catch (NumberFormatException e) {
				System.err.println("Invalid number: " + number);
			}
		}
		
		return validList;
	}
	
	// 4. Same as 3, but to return the errors
	public static String getErrors(String contents) {
		String[] numbers = contents.split("\\s+");
		List<String> errors = new ArrayList<>();
		
		for (String number : numbers) {
			try {
				int num = Integer.parseInt(number);
			}
			catch (NumberFormatException e) {
				errors.add(number);
			}
		}
		
		String errorString = errors.toString();
		return errorString;
	}
	// 5. Calculate Mean
	public double calcMean() {
		double mean = 0.0;
		for (Node current = head; current != null; current = current.next) {
			// Add up all the numbers
			mean += current.num;
		}
		mean /= size;
		return mean;
	}
	
	// 6. Calculate Standard Deviation
	public double calcStdDev() {
		double mean = calcMean();
		double sumSquaredDiffs = 0.0;
		
		for (Node current = head; current != null; current = current.next) {
			double diff = current.num - mean;
			sumSquaredDiffs += diff * diff;
		}
		
		double variance = sumSquaredDiffs / size;
		return Math.sqrt(variance);
	}
}
