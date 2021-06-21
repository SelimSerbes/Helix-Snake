

public class Node {
	   Object data;
	   Node link; 
	   int x,y;

	   public Node(Object dataToAdd,int x,int y) {
		     data = dataToAdd;
		     link = null;
		     this.x=x;
		     this.y=y;
	   }
	   
	   public Object getData() { return data; }
	   public void setData(Object data) { this.data = data;  }

	   public Node getLink() { return link;  }
	   public void setLink(Node link) { this.link = link;   }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
