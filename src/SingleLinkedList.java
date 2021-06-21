
public class SingleLinkedList {
	private Node head;

	public SingleLinkedList() {
		head = null;
	}

	public void addToEnd(Object dataToAdd, int x, int y) {
		Node newNode = new Node(dataToAdd, x, y);

		if (head == null) {
			head = newNode;
		} else {
			Node temp = head;

			while (temp.getLink() != null) {
				temp = temp.getLink();
			}

			temp.setLink(newNode);
		}
	}

	public void addTohead(Object dataToAdd, int x, int y) {
		Node newNode = new Node(dataToAdd, x, y);

		if (head == null) {
			head = newNode;
		} else {
			if (true) {
				Node newnode = new Node(dataToAdd, x, y);
				newnode.setLink(head);
				head = newnode;
			}
		}
	}

	public String display_head_xy() {
		String output = "";

		Node temp = head;
		output = temp.getX() + " " + temp.getY() + " ";
		/*
		 * while (temp != null) { output += temp.getX() + " " + temp.getY() + " "; temp
		 * = temp.getLink(); }
		 */

		return output;
	}

	public String display() {
		String output = "";

		Node temp = head;

		while (temp != null) {
			output += temp.getData() + " ";
			temp = temp.getLink();
		}

		return output;
	}

	public void direction(int px, int py) {

		Node temp = Snake.Snake_SLL.head;
		if (temp.getX() != px || temp.getY() != py) {
			int x_temp = temp.getX();
			int y_temp = temp.getY();
			int i = 0;
			int x_temp2 = 0;
			int y_temp2 = 0;

			if (temp == head) {
				temp.setX(px);
				temp.setY(py);
				// Game_Management.game_area[px][py] = (char) temp.getData();
			}
			temp = temp.getLink();

			while (temp != null) {
				// py--;
				x_temp2 = temp.getX();
				y_temp2 = temp.getY();

				temp.setX(x_temp);
				temp.setY(y_temp);

				// Game_Management.game_area[x_temp][y_temp] = (char) temp.getData();
				/*
				 * if (temp.getLink() == null) { Game_Management.game_area[px][py] = ' '; }
				 */
				x_temp = x_temp2;
				y_temp = y_temp2;
				temp = temp.getLink();
				i++;
				if (i == Game_Management.snake_size - 1) {
					// py--;
					Game_Management.game_area[x_temp][y_temp] = ' ';
					break;
				}
			}
			Game_Management.screen.getTextWindow().setCursorPosition(69, 27);
			System.out.print("      "); // Pause deleting.
		}
	}

	public void delete_snake() {
		Node temp = head;
		while (temp != null) {
			Game_Management.game_area[temp.getX()][temp.getY()] = ' ';
			temp = temp.getLink();
		}

	}

	/*
	 * public void delete_1x_1y(int direction) { Node temp = head; while (temp !=
	 * null) { if(direction==0) { temp.setX(temp.getX()+1); } else if(direction==1)
	 * { temp.setX(temp.getY()+1); } else if(direction==2) {
	 * temp.setX(temp.getX()-1); } else if(direction==3) { temp.setX(temp.getY()-1);
	 * }
	 * 
	 * temp=temp.getLink(); } }
	 */
	public void clashing() {
		Node temp = head;
		int head_x = temp.getX();
		int head_y = temp.getY();
		temp = temp.getLink();
		while (temp != null) {
			if (head_x == temp.getX() && head_y == temp.getY()) {
				Game_Management.flag_bait = true;
			}
			temp = temp.getLink();
		}

	}

	public void reverse_snake() {
		Node temp = head;
		String codon = "";
		if (size() == 3) {
			while (temp != null) {
				codon += String.valueOf(temp.getData());
				temp = temp.getLink();
			}

			while (Snake.Snake_codon.size() != 0) {
				Snake.Snake_codon.removeFirst_char();
			}

		}

		Game_Management.aminoacids.search_codon(codon);
	}

	public void printSnake() {
		Node temp = head;
		while (temp != null) {
			Game_Management.game_area[temp.getX()][temp.getY()] = (char) temp.getData();
			temp = temp.getLink();
		}
	}

	public void removeAll(Object dataToRemove) {
		if (head == null) {
			System.err.println("The Linked List is empty");
		} else {
			while ((int) head.getData() == (int) dataToRemove && head.getData() != null) {
				head = head.getLink();
				if (head == null) {
					break;
				}
			}

			Node temp = head;
			Node prev = null;
			while (temp != null) {

				if ((int) temp.getData() == (int) dataToRemove) {
					prev.setLink(temp.getLink());
					temp = temp.getLink();
				} else {
					prev = temp;
					temp = temp.getLink();
				}
			}
		}
	}

	public boolean removeOne(Object dataToRemove) {
		if (head == null) {
			System.err.println("The Linked List is empty");
			return false;
		}

		else if ((int) head.getData() == (int) dataToRemove) {
			head = head.getLink();
			return true;
		} else {
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((int) temp.getData() == (int) dataToRemove) {
					prev.setLink(temp.getLink());
					return true;
				}
				prev = temp;
				temp = temp.getLink();
			}
			System.out.println("Element" + dataToRemove + "could not be found!");
			return false;
		}
	}

	public int removeFirst() {
		int temp2 = (int) head.getData();
		if (head == null) {
			System.err.println("The Linked List is empty");

		}

		else if ((int) head.getData() == (int) temp2) {
			head = head.getLink();
		} else {
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((int) temp.getData() == (int) temp2) {
					prev.setLink(temp.getLink());
					break;
				}
				prev = temp;
				temp = temp.getLink();
			}
			System.out.println("Element" + temp2 + "could not be found!");
		}
		return temp2;
	}

	public char removeFirst_char() {
		char temp2 = (char) head.getData();
		if (head == null) {
			System.err.println("The Linked List is empty");

		}

		else if ((char) head.getData() == (char) temp2) {
			head = head.getLink();
		} else {
			Node temp = head;
			Node prev = null;
			while (temp != null) {
				if ((char) temp.getData() == (char) temp2) {
					prev.setLink(temp.getLink());
					break;
				}
				prev = temp;
				temp = temp.getLink();
			}
			System.out.println("Element" + temp2 + "could not be found!");
		}
		return temp2;
	}

	public int size() {
		int count = 0;
		if (head == null) {
			// System.err.println("The Linked List is empty");
		} else {
			Node temp = head;
			while (temp != null) {
				count++;
				temp = temp.getLink();
			}

		}
		return count;
	}

	public boolean search(Object dataToSearch) {
		if (head == null) {
			return false;
		} else {
			Node temp = head;

			boolean retVal = false;
			while (temp != null) {
				if ((int) temp.getData() == (int) dataToSearch) {
					retVal = true;
					break;
				}

				temp = temp.getLink();
			}

			return retVal;
		}
	}

	public boolean search_char(Object dataToSearch) {
		if (head == null) {
			return false;
		} else {
			Node temp = head;

			boolean retVal = false;
			while (temp != null) {
				if ((char) temp.getData() == (char) dataToSearch) {
					retVal = true;
					break;
				}

				temp = temp.getLink();
			}

			return retVal;
		}
	}

	public int findMax() {
		if (head == null) {
			System.err.println("The Linked List is empty");
			return Integer.MIN_VALUE;
		} else {

			int maxVal = Integer.MIN_VALUE;

			Node temp = head;

			while (temp != null) {
				if ((int) temp.getData() > maxVal) {
					maxVal = (int) temp.getData();
				}

				temp = temp.getLink();
			}

			return maxVal;
		}
	}

}
