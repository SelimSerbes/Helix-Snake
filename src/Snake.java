import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Snake {
	static SingleLinkedList Snake_SLL = new SingleLinkedList();
	static SingleLinkedList Snake_codon = new SingleLinkedList();
	static int codon_count = 0;
	public int keypr;
	public int rkey;
	public KeyListener klis;
	Game_Management a = new Game_Management();
	int x = Game_Management.px, y = Game_Management.py - 2;
	boolean eat_flag = false;

	public void Snake_random_initial() {
		for (int i = 0; i < 3; i++) {
			char letter = Random_letter();
			Snake_SLL.addTohead(letter, x, y);// use addtoend instead of addtohead

			Snake_codon.addToEnd(letter, x, y);//reverse snake

			Game_Management.game_area[x][y] = letter;
			y++;
		}
	}

	public char Random_letter() {
		char[] letters = { 'A', 'C', 'G', 'T' };
		int rnd = (int) (Math.random() * 4);
		return letters[rnd];
	}

	public void moving() throws InterruptedException {

		klis = new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}

		};
		Game_Management.screen.getTextWindow().addKeyListener(klis);
		
		int direction = 99;
		boolean reverse_direction = false;
		rkey = KeyEvent.VK_RIGHT;
		boolean music_flag = false;
		while (true) {
			if (keypr == 1 || keypr == 0) {
				if (rkey == KeyEvent.VK_LEFT && direction != 3) {
					Game_Management.py--;
					direction = 1;
					Game_Management.time += 0.1;
					music_flag=true;
				} else if (rkey == KeyEvent.VK_RIGHT && direction != 1) {
					Game_Management.py++;
					direction = 3;
					Game_Management.time += 0.1;
					music_flag=true;
				} else if (rkey == KeyEvent.VK_UP && direction != 2) {
					Game_Management.px--;
					direction = 0;
					Game_Management.time += 0.1;
					music_flag=true;
				} else if (rkey == KeyEvent.VK_DOWN && direction != 0) {
					Game_Management.px++;
					direction = 2;
					Game_Management.time += 0.1;
					music_flag=true;
				} else {
					if (rkey == KeyEvent.VK_UP) {
						rkey = KeyEvent.VK_DOWN;
					} else if (rkey == KeyEvent.VK_DOWN) {
						rkey = KeyEvent.VK_UP;
					} else if (rkey == KeyEvent.VK_RIGHT) {
						rkey = KeyEvent.VK_LEFT;
					} else if (rkey == KeyEvent.VK_LEFT) {
						rkey = KeyEvent.VK_RIGHT;
					}else if (music_flag == true) {
						File music = new File("Pause.wav");
						a.play_music_wav(music);
						Game_Management.screen.getTextWindow().setCursorPosition(69, 27);
						System.out.print("PAUSED");
						music_flag=false;
					}
					reverse_direction = true;
				}
				Snake_SLL.clashing();
				Snake_codon.reverse_snake();
				
				eating(Game_Management.px, Game_Management.py, direction);
				Snake.Snake_SLL.direction(Game_Management.px, Game_Management.py);		
				a.Screen();
				
				if (Game_Management.flag_bait == true) {
					Game_Management a=new Game_Management();
					File music = new File("death.wav");
					a.play_music_wav(music);
					a.Delete_Screen();
					break;
				}
				keypr = 0;
			}
			if (reverse_direction == false) {
				TimeUnit.MILLISECONDS.sleep(100);
			} else {
				reverse_direction = false;
			}
		}
	}

	public void eating(int px, int py, int direction) {
		char[] characters = { 'A', 'C', 'G', 'T' };
		for (int i = 0; i < characters.length; i++) {
			if (Game_Management.game_area_backup[px][py] == '#') {
				Game_Management.flag_bait = true;
			} else if (Game_Management.game_area_backup[px][py] == characters[i]) {
				Game_Management.game_area_backup[px][py] = ' ';
				Snake.Snake_SLL.addTohead(characters[i], px, py);
				Snake.Snake_codon.addToEnd(characters[i], px, py);
				Game_Management a = new Game_Management();
				Game_Management.snake_size++;
				Game_Management.score += 5;
				a.Map_random_one();
				File music = new File("Kick.wav");
				a.play_music_wav(music);
			}
		}
	}

}
