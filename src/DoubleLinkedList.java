public class DoubleLinkedList {
	
	private Double_Node head;	
	private Double_Node tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
 
	public void add(int dataToAdd,String name) {
		Double_Node newnode;
		if (head == null) {  //list is empty
			newnode = new Double_Node(dataToAdd,name); 
			head = newnode;
			tail = newnode;	     
		}
		else {   //add to the end
			newnode = new Double_Node(dataToAdd,name); 
			newnode.setPrev(tail);
			tail.setNext(newnode);				
			tail=newnode;			
		}
	}
	public void addbetween(int dataToAdd,String name) {
		Double_Node newnode=new Double_Node(dataToAdd,name);
		if (head == null) {  //list is empty
			//newnode = new Double_Node(dataToAdd);
			head = newnode;
			tail = newnode;
		}
		else {   
			Double_Node temp=head;
			while(temp.getNext()!=null && dataToAdd>(int)temp.getNext().getData()){
				temp=temp.getNext();
			}
			newnode.setPrev(temp);
			newnode.setNext(temp.getNext());
			if(temp.getNext()!=null)
				temp.getNext().setPrev(newnode);
			else
				tail=newnode;
			temp.setNext(newnode);
		}
	}




	public void remove(Integer s)
	{		
		if (head == null)    
			System.out.println("linked list is empty");
		else   
		{
			while (((Integer) head.getData()).equals(s))	{	   
				head = head.getNext();
				head.setPrev(null);
			}
			Double_Node temp = head;
			while (temp != null)
			{
				if (((Integer)temp.getData()).equals(s)) {
					if (temp.getNext() == null) {
						tail = tail.getPrev();
						tail.setNext(null);
					}
					else {
						temp.getPrev().setNext(temp.getNext());
						temp.getNext().setPrev(temp.getPrev());
					}		    			
				}
				temp=temp.getNext();
			}    	
		}
	}

	public int size()
	{
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			Double_Node temp = head;
			while (temp != null)
			{
				count++;
				temp=temp.getNext();
			}
		}
		return count;   
	}

	public void display1()
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Double_Node temp = head;
			temp=temp.getNext();
			while (temp != null)
			{
				System.out.print(temp.getData() + " ");
				temp = temp.getNext();
			}
			System.out.println();
		}
	}	
	
	public void new_display()
	{
		int loop_count=0;
		int i=1;
		int y_coordinat=4;
		
		Game_Management.screen.getTextWindow().setCursorPosition(43, y_coordinat-1);
		System.out.print("* PLAYER NAME -------");
		Game_Management.screen.getTextWindow().setCursorPosition(64, y_coordinat-1);
		System.out.print("- SCORE -");
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Double_Node temp = tail;
			while (temp != null)
			{
				if(temp.getPrev()==null) {
					break;
				}
				Game_Management.screen.getTextWindow().setCursorPosition(43, y_coordinat);
				System.out.print(i+" - "+temp.getName());
				Game_Management.screen.getTextWindow().setCursorPosition(67, y_coordinat);
				System.out.print(temp.getData());
				//System.out.print(temp.getData() + " ");
				temp = temp.getPrev();
				y_coordinat+=2;
				i++;
				loop_count++;
				if(loop_count==10) {
					break;
				}
			}
			System.out.println();
		}
	}
	public void display2()
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Double_Node temp = tail;
			while (temp != null)
			{
				if(temp.getPrev()==null) {
					break;
				}
				System.out.print(temp.getData() + " ");
				temp = temp.getPrev();
				
			}
			System.out.println();
		}
	}

	public boolean search(Integer s)
	{
		boolean flag = false;
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Double_Node temp = head;
			while (temp != null)
			{
				if (temp.getData().equals(s)) {
					flag = true;
					break;
				}
				temp = temp.getNext();
			}	    
		}
		return flag;
	}

	public void display()
	{
		if (head == null)    
			System.out.println("linked list is empty");
		else {
			Double_Node temp1 = head;
			Double_Node temp2 = tail;
			while (temp1 != temp2 && temp1 != temp2.getNext())
			{
				System.out.print(temp1.getData() + " ");
				System.out.print(temp2.getData() + " ");
				temp1 = temp1.getNext();
				temp2 = temp2.getPrev();
			}
			if(temp1 == temp2) System.out.print(temp1.getData());
			System.out.println();
		}
	}

	public Object getElement(int x)
	{
		
		if (head == null)    
		{
			System.out.println("linked list is empty");
			return null;
		}
		else if(x > size())
		{
			System.out.println("index is out of range");
			return null;
		}
		else {
			Double_Node temp = head;
			int count = 1;
			while (temp != null)
			{
				if (count == x) {
					return temp.getData();
				}
				temp = temp.getNext();
				count++;
			}	    
		}
		return null;
	}
}
