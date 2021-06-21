public class MultiLinkedList {
	CategoryNode head;

	public void search_codon(String codon) {
		int point = 0;
		CategoryNode temp = head;

		while (temp != null && codon != "") {
			ItemNode temp2 = temp.getRight();
			while (temp2 != null) {
				if (String.valueOf(temp2.getItemName()).contains(codon)) {
					Snake.codon_count++;
					String[] temp_array = temp2.getItemName().split("-");
					point = Integer.valueOf(temp_array[1]);
					Game_Management.screen.getTextWindow().setCursorPosition(69, Snake.codon_count + 1);
					System.out.print(temp_array[0] + " - " + temp_array[1]);
					Game_Management.score += point;
					Game_Management.screen.getTextWindow().setCursorPosition(69, 0);
					System.out.print("SCORE : " + Game_Management.score);
				}
				temp2 = temp2.getNext();
			}
			temp = temp.getDown();
		}
	}

	public void addCategory(String dataToAdd) {
		CategoryNode temp;
		if (head == null) {
			temp = new CategoryNode(dataToAdd);
			head = temp;
		} else {
			temp = head;
			while (temp.getDown() != null)
				temp = temp.getDown();
			CategoryNode newnode = new CategoryNode(dataToAdd);
			temp.setDown(newnode);
		}
	}

	public void addItem(String Category, String Item) {
		if (head == null)
			System.out.println("Add a Category before Item");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				if (Category.equals(temp.getCategoryName())) {
					ItemNode temp2 = temp.getRight();
					if (temp2 == null) {
						temp2 = new ItemNode(Item);
						temp.setRight(temp2);
					} else {
						while (temp2.getNext() != null)
							temp2 = temp2.getNext();
						ItemNode newnode = new ItemNode(Item);

						temp2.setNext(newnode);
					}
				}
				temp = temp.getDown();
			}
		}
	}

	public int sizeCategory() {
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				count++;
				temp = temp.getDown();
			}
		}
		return count;
	}

	public int sizeItem() {
		int count = 0;
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				ItemNode temp2 = temp.getRight();
				while (temp2 != null) {
					count++;
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
			}
		}
		return count;
	}

	public void display() {
		if (head == null)
			System.out.println("linked list is empty");
		else {
			CategoryNode temp = head;
			while (temp != null) {
				System.out.print(temp.getCategoryName() + " --> ");
				ItemNode temp2 = temp.getRight();
				while (temp2 != null) {
					System.out.print(temp2.getItemName() + " ");
					temp2 = temp2.getNext();
				}
				temp = temp.getDown();
				System.out.println();
			}
		}
	}
}