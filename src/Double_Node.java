public class Double_Node {
   
    private Object data;
    private Double_Node prev; 
    private Double_Node next;
    String name;

   public Double_Node(Object dataToAdd,String name) {
     data = dataToAdd;
     prev = null;
     next = null;
     this.name=name;
   }
	   
   public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Object getData() {
     return data;
   }

   public void setData(Object data) {
     this.data = data;
   }

   public Double_Node getNext() {
     return next; 
   }

   public void setNext(Double_Node next) {
     this.next = next;
   }
   
   public Double_Node getPrev() {
	 return prev; 
   }

   public void setPrev(Double_Node prev) {
     this.prev = prev;
   }
}
