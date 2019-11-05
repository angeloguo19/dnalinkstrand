/*Authors: Martha Aboagye, Annabel Howell, Angelo Guo
 */

public class LinkStrand implements IDnaStrand {
	private Node myFirst, myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	private class Node{
		String info;
		Node next;

		public Node(String s) {
			info = s;
			next =null;
		}
	}

	public LinkStrand() { this(""); }

	/**
	 * Create a strand representing s. No error checking is done to see if s
	 * represents valid genomic/DNA data.
	 *
	 * @param s
	 *            is the source of cgat data for this strand
	 */
	public LinkStrand(String s) { initialize(s); }

	/**
	 * Initialize this strand so that it represents the value of source. No
	 * 	 error checking is performed.
	 * @param source
	 * 			is the source of this enzyme
	 */
	@Override
	public void initialize(String source) {
		myFirst = new Node(source);
		myLast = myFirst;
		myAppends = 0;
		mySize = source.length();
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}

	/**
	 * Returns a LinkStrand object
	 * @param source is data from which object constructed
	 * @return
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}

	/**
	 * Returns the size of the LinkStrand
	 * @return the total number of characters stored in all the nodes
	 */
	@Override
	public long size() {
		// TODO Auto-generated method stub
		return mySize;
	}

	/**
	 * Adds a new node to the end of the internal linked list and updates the states to maintain the invariant.
	 * @param dna
	 *            is the string appended to this strand
	 * @return The current modified LinkStrand
	 */
	@Override
	public IDnaStrand append(String dna) {
		// TODO Auto-generated method stub
		myLast.next = new Node(dna);
		myLast = myLast.next;
		myAppends++;
		mySize += dna.length();
		return this;
	}

	/**
	 * Helper function that adds a new node to the front of the iternal linked list and updates the states to maintain the invariant.
	 * @param dna
	 * 				is the string appended to this strand
	 */
	private void frontAppend(String dna) {
		Node temp = new Node(dna);
		temp.next = myFirst;
		myFirst = temp;
		mySize += dna.length();
	}

	/**
	 * Create a new LinkStrand object that is the reverse of the object on which it's called.
	 * @return A new reversed LinkStrand object.
	 */
	@Override
	public IDnaStrand reverse() {
		// TODO Auto-generated method stub
		StringBuilder rb = new StringBuilder(myFirst.info);
		LinkStrand r = new LinkStrand(rb.reverse().toString());
		Node list = myFirst.next;
		while (list!=null) {
			rb = new StringBuilder(list.info);
			r.frontAppend(rb.reverse().toString());
			list = list.next;
		}
		return r;
	}

	/**
	 * The number of times .append() has been called on the LinkedStrand.
	 * @return the instance variable myAppends.
	 */
	@Override
	public int getAppendCount() {
		// TODO Auto-generated method stub
		return myAppends;
	}

	/**
	 * Concatenates the strings stored in each node of the internal linked list.
	 * @return the String representation of the entire DNA strand.
	 */
	public String toString() {
		Node create = myFirst;
		StringBuilder dna = new StringBuilder();
		while (create != null) {
			dna.append(create.info);
			create = create.next;
		}
		return dna.toString();
	}

	/**
	 * Finds a character at a specific index in a linked list of strings.
	 * @param index specifies which character will be returned
	 * @return the character in the dna strand at the specified index.
	 */
	@Override
	public char charAt(int index) {
		// TODO Auto-generated method stub
		if (index > mySize - 1 || 0 > index) {
			throw new IndexOutOfBoundsException("Index " + " is out of bounds!");
		}
		if (index < myIndex) {
			myIndex = 0;
			myLocalIndex = 0;
			myCurrent = myFirst;
		}
		while (myIndex != index) {
			myIndex++;
			myLocalIndex++;
			if (myLocalIndex >= myCurrent.info.length()) {
				myLocalIndex = 0;
				myCurrent = myCurrent.next;
			}
		}
		return myCurrent.info.charAt(myLocalIndex);
	}
}