
public class LinkedNumber {

	private int base;
	private DLNode <Digit> front, rear;
	
	/**
	 * Checks if num is empty, then converts num into a DLL
	 * Constructor
	 * 
	 * @param num String "num" converted into a DLL
	 * @param baseNum int "baseNum" tells u the base
	 */
	public LinkedNumber(String num, int baseNum) {
		
		base = baseNum;
		if (num == null || num.isEmpty()) {
			throw new LinkedNumberException("no digits given");
		}
		
		
		DLNode<Digit> pnode,node;
		node = new DLNode(new Digit(num.charAt(0)));
		
		pnode = front = node; // set front to the first node
		
		for (int i = 1; i<num.length();i++) {
			node = new DLNode(new Digit(num.charAt(i)));
			pnode.setNext(node);
			node.setPrev(pnode);
			pnode = node;
		}
		rear = node; // set rear to the rear node
		front.setPrev(null);
		rear.setNext(null);
		
	}
	/**
	 * Sets Base to 10 by default
	 * Assigns integer number into a DLL 
	 * @param Constructor int "num" into DLL
	 */
	public LinkedNumber(int num) {
		base = 10;
		String numar = String.valueOf(num);
		
		if (numar == null || numar.isEmpty()) {
			throw new LinkedNumberException("no digits given/");
		}
		
		DLNode<Digit> pnode,node;
		
		
		node = new DLNode(new Digit(numar.charAt(0)));
		
		pnode = front = node; // set front to the first node
		
		
		
		for (int i = 1; i<numar.length();i++) {
			node = new DLNode(new Digit(numar.charAt(i)));
			node.setPrev(pnode);
			pnode.setNext(node);
			pnode = node;
		}
		rear = node; // set rear to the rear node
		front.setPrev(null);
		rear.setNext(null);
		
	}
	/**
	 * Returns T/F whether or not the element is within the range of the number
	 * @return True if all nodes are below base, False otherwise
	 */
	public boolean isValidNumber() {
		 
		 DLNode<Digit> curr = front;
		 int number = base;
		 
		
		 
		 while (curr != null) {
			 if (curr.getElement().getValue() == -1) {
				 return false;
			 }
			 if (curr.getElement().getValue() > number-1) {
				 return false;
			 }
			 curr = curr.getNext();
		 }
			 
			 
			 
		 
		 
		 
		return true;

		
	}
	
	/**
	 * 	
	 * @return Return Int Base
	 */
	public int getBase() {
		return base;
	}
	/**
	 * 
	 * @return Return Front Node
	 */
	public DLNode<Digit> getFront(){
		return front;
	}
	
	/**
	 * 
	 * @return Return Rear Node
	 */
	
	public DLNode<Digit> getRear(){
		return rear;
	}
	/**
	 * Returns int of the length of the doubly Linked List
	 * @return int Length of DLL
	 */
	
	public int getNumDigits() {
		int counter = 0;
		DLNode<Digit> curr = front;
		
		while (curr != null) {
			
			counter++;
			
			curr = curr.getNext();
		}
		return counter;
		
		
	}
	
	
	/**
	 * Returns a string of the input
	 * (Not sure if I should convert the hex letters into digits)
	 * @return String of input
	 */
	public String toString() {
		
		
		DLNode<Digit> curr = front;
		
		String Strang ="";
		
		while (curr != null) {
			
			
			Strang += curr.getElement();
			curr = curr.getNext();
		}
		return (Strang);
		
	}
	/**
	 * Returns true if base and every value is equal in both nodes
	 * @param other DLL
	 * @return True if base and node values are equal
	 */
	public boolean equals(LinkedNumber other) {
		if (this.base != other.base) {
			return false;
			
		}else 
		{
			DLNode<Digit> curr1 = this.front;
			DLNode<Digit> curr2 = other.front;
			
			while (curr1 != null) {
				
				if (curr1.getElement().getValue() != curr2.getElement().getValue()) {
					return false;
				}
				
				curr1 = curr1.getNext();
				curr2 = curr2.getNext();
				
			}
			
			
		}
		return true;
	}
	
	/**
	 * (Might want to change it so it is a try/catch instead of a if/throw)
	 * Converts the base if the base is valid with the objects in the linked list
	 * @param newBase
	 * @return LinkedNumber with a new base
	 */
	public LinkedNumber convert (int newBase) {
		if (!isValidNumber()) {
			throw new LinkedNumberException("cannot convert invalid number");
		}
		
		LinkedNumber converted = new LinkedNumber(toString(),newBase);
		
		String val = toString();
		int tempBase = getBase();
		
		//Attempt at Decimal to Non-Decimal (not sure if I can even handle decimals)
		if (tempBase == 10 && newBase != 10) {
			int sumval = 0;
			DLNode<Digit> curr = rear;
			
			int nodeCounter = 0;
			int power = 0;
			while (curr != null) {
				int fortemp = 1;
				for (int i = 0; i < power; i++) {
					fortemp *= 10;
					
				}
				sumval += curr.getElement().getValue()*fortemp;
				curr = curr.getPrev();
				power++;
				nodeCounter++;
			}
			nodeCounter++;
			
			
			
			
			
			
			DLNode<Digit> nodeElement, nodeRear;
			
			String stringHolder = "";
			
			nodeElement = new DLNode(new Digit('A'));
			
			front = nodeRear = nodeElement;
			
			while (sumval != 0) {
				
				int temp = sumval % newBase;
					if (temp >= 10) {
						String stringTemp;
						switch (temp){
						case 10:
							stringTemp = "A";
						case 11:
							 stringTemp = "B";
						case 12:
							 stringTemp = "C";
						case 13:
							 stringTemp = "D";
						case 14:
							 stringTemp = "E";
						case 15:
							 stringTemp = "F";
						
						
						stringHolder = stringTemp + stringHolder;
						}
					}
					
					else {
						String stringTemp = String.valueOf(temp);
						stringHolder = stringTemp + stringHolder;
					
				}
					sumval /= newBase;
					
			}
			
			
			
			LinkedNumber digitToNon = new LinkedNumber(stringHolder.toString(),newBase);
			return digitToNon;
			}
		
		
		
		
		
		// From non-Decimal to decimal ============================================================================================================
		
		if (tempBase != 10 && newBase == 10) {
			int sumval = 0;
			DLNode<Digit> curr = rear;
			
			int nodeCounter = 0;
			int power = 0;
			while (curr != null) {
				int fortemp = 1;
				for (int i = 0; i < power; i++) {
					fortemp *= tempBase;
					
				}
				sumval += curr.getElement().getValue()*fortemp;
				curr = curr.getPrev();
				power++;
				nodeCounter++;
			}
			nodeCounter++;
			String strinVal = String.valueOf(sumval);
			
			LinkedNumber nonToDigit = new LinkedNumber(strinVal,newBase);
			return nonToDigit;
		}
		
		
		
		
		
		// From non-Decimal to non-Decimal 
		
			if (tempBase != 10 && newBase != 10) {
				int sumval = 0;
				DLNode<Digit> curr = rear;
				
				int nodeCounter = 0;
				int power = 0;
				while (curr != null) {
					int fortemp = 1;
					for (int i = 0; i < power; i++) {
						fortemp *= tempBase;
						
					}
					sumval += curr.getElement().getValue()*fortemp;
					curr = curr.getPrev();
					power++;
					nodeCounter++;
				}
				nodeCounter++;
				
				String strinVal = String.valueOf(sumval);
			
			
				
			
				DLNode<Digit> nodeElement, nodeRear;
				
				StringBuilder stringHolder = new StringBuilder();
				
				nodeElement = new DLNode(new Digit('A'));
				
				front = nodeRear = nodeElement;
				
				while (sumval != 0) {
					
					int temp = sumval % newBase;
						if (temp >= 10) {
							String stringTemp;
							switch (temp){
							case 10:
								stringTemp = "A";
							case 11:
								 stringTemp = "B";
							case 12:
								 stringTemp = "C";
							case 13:
								 stringTemp = "D";
							case 14:
								 stringTemp = "E";
							case 15:
								 stringTemp = "F";
							
							
							stringHolder.insert(0,stringTemp);
							}
						}
						
						else {
							String stringTemp = String.valueOf(temp);
							stringHolder.insert(0,stringTemp);
						
					}
						sumval /= newBase;
						
				}
				LinkedNumber converted1 = new LinkedNumber(stringHolder.toString(),newBase);
				return converted1;
			}
			
		
		
	
		return converted;
		
	
		}
		

		
	
	public void addDigit(Digit digit, int position) {
		if(toString().length()+1 < position || position <0) {
			throw new LinkedNumberException("invalid position");
		}
		
		
		DLNode newNode = new DLNode(digit);
		
		
		if (position == 0) { 			// im glad my amazing professor prepared us fully for this!
			rear.setNext(newNode);
			newNode.setPrev(rear);
			rear = newNode;
			rear.setNext(null);
		}
		if (position == toString().length()) { 
			newNode.setNext(front);
			front.setPrev(newNode);
			front = newNode;
			front.setPrev(null);

		}
		if (position > 0 && position < toString().length()-1) {
			DLNode<Digit> curr;
			curr = rear;
			int counter = 0;
			for (int i = 0; i < position;i++) {
				curr = curr.getPrev();
			}
			
			newNode.setNext(curr.getNext());
			curr.getNext().setPrev(newNode);
			curr.setNext(newNode);
			newNode.setPrev(curr);
		}	
		
	}
		
	public int removeDigit(int position) {
		
		if(toString().length()+1 < position || position < 0) {
			throw new LinkedNumberException("invalid position");
		}
		
		
		
		
		// Remove Rear
		if (position == 0) {
			DLNode<Digit> curr = rear;
			rear.getPrev().setNext(null);
			rear = rear.getPrev();
			
			LinkedNumber RemoveRear = new LinkedNumber(String.valueOf(curr.getElement().getValue()),getBase());
			return Integer.parseInt(RemoveRear.convert(10).toString());
			
		}
		// Remove Front
		if (position == toString().length()-1) {
			DLNode<Digit> curr = front;
			DLNode<Digit> currRear = rear;
			
		
			int fortemp = 10;
			for (int i = 0; i < position-1;i++) {
				currRear = currRear.getPrev();
				fortemp *= 10;
			}

		
			front.getNext().setPrev(null);
			front = front.getNext();
		
			
			
			LinkedNumber RemoveRear = new LinkedNumber(String.valueOf(curr.getElement().getValue()*fortemp),getBase());
			return Integer.parseInt(RemoveRear.convert(10).toString());
			
		}
		
		// remove something within
		if (position > 0 && position < toString().length()) {
		DLNode<Digit> curr = rear;
		
		int fortemp = 1;
		for (int i = 0; i < position;i++) {
			curr = curr.getPrev();
			fortemp *= 10;
			
		}
		
		
		
		curr.getNext().setPrev(curr.getPrev());
		curr.getPrev().setNext(curr.getNext());

		
		LinkedNumber RemoveRear = new LinkedNumber(String.valueOf(curr.getElement().getValue()*fortemp),getBase());
		return Integer.parseInt(RemoveRear.convert(10).toString());
	}
		return -1;
	}
		
		
	
	
	
	
	
	public static void main(String[] args) {
		LinkedNumber ln = new LinkedNumber("32175267", 8);
		int v1 = ln.removeDigit(7);
		System.out.println(v1);
		System.out.println(ln.toString());
		
		
		

	}

}

