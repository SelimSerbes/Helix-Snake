import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
	public int keypr;
	public int rkey;
	public KeyListener klis;
	Game_Management a = new Game_Management();
	Snake b = new Snake();
	Scanner scan = new Scanner(System.in);
	Scanner scan2 = new Scanner(System.in);
	Scanner scan3 = new Scanner(System.in);

	public void menu() throws InterruptedException, IOException {
		a.file_reader_multi();
		
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
		int px = 12;
		Game_Management.screen.getTextWindow().setCursorPosition(47, px);
		System.out.print("►");
		while (true) {
			Game_Management.screen.getTextWindow().setCursorPosition(20, 2);
			System.out.print("  _    _ ______ _      _______   __   _____ _   _          _  ________ ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 3);
			System.out.print(" | |  | |  ____| |    |_   _\\ \\ / /  / ____| \\ | |   /\\   | |/ /  ____|");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 4);
			System.out.print(" | |__| | |__  | |      | |  \\ V /  | (___ |  \\| |  /  \\  | ' /| |__   ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 5);
			System.out.print(" |  __  |  __| | |      | |   > <    \\___ \\| . ` | / /\\ \\ |  < |  __|  ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 6);
			System.out.print(" | |  | | |____| |____ _| |_ / . \\   ____) | |\\  |/ ____ \\| . \\| |____ ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 7);
			System.out.print(" |_|  |_|______|______|_____/_/ \\_\\ |_____/|_| \\_/_/    \\_\\_|\\_\\______|");

			Game_Management.screen.getTextWindow().setCursorPosition(20, 22);
			System.out.print("                      __    __    __    __");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 23);
			System.out.print("                     /  \\  /  \\  /  \\  /  \\");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 24);
			System.out.print("____________________/  __\\/  __\\/  __\\/  __\\_____________________________");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 25);
			System.out.print("___________________/  /__/  /__/  /__/  /________________________________");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 26);
			System.out.print("                   | / \\   / \\   / \\   / \\  \\____");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 27);
			System.out.print("                   |/   \\_/   \\_/   \\_/   \\    o \\");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 28);
			System.out.print("                                           \\_____/--<");

			Game_Management.screen.getTextWindow().setCursorPosition(48, 12);
			System.out.print(" Start Game");
			Game_Management.screen.getTextWindow().setCursorPosition(48, 14);
			System.out.print(" Score Table");
			Game_Management.screen.getTextWindow().setCursorPosition(48, 16);
			System.out.print(" Exit");

			if (keypr == 1) {

				if (rkey == KeyEvent.VK_UP) {
					px -= 2;
					Game_Management.screen.getTextWindow().setCursorPosition(46, px + 2);
					System.out.print("  ");
					File music = new File("menu_select.wav");
					a.play_music_wav(music);
				} else if (rkey == KeyEvent.VK_DOWN) {
					px += 2;
					Game_Management.screen.getTextWindow().setCursorPosition(46, px - 2);
					System.out.print("  ");
					File music = new File("menu_select.wav");
					a.play_music_wav(music);
				}
				if (rkey == KeyEvent.VK_ENTER && px == 12) {
					a.RESET();
					File music = new File("opening.wav");
					a.play_music_wav(music);
					a.game_loading();
					
					// music=new File("super_mario64.wav"); 
					// a.play_music_wav(music);
					 
					b.moving();
					while (true) {
						Game_Management.screen.getTextWindow().setCursorPosition(48, 10);
						System.out.println("Game Over!!!");
						Game_Management.screen.getTextWindow().setCursorPosition(43, 18);
						System.out.print("Your name : ");
						Game_Management.player_name = scan2.nextLine();
						if (!Game_Management.player_name.contains(";")) {
							break;
						} else {
							Game_Management.screen.getTextWindow().setCursorPosition(46, 20);
							System.out.print("Don't use \";\".");
							TimeUnit.MILLISECONDS.sleep(1000);
						}
						a.Delete_Screen();
					}
					a.file_writer(Game_Management.player_name, Game_Management.score);
					a.Delete_Screen();
				} else if (rkey == KeyEvent.VK_ENTER && px == 14) {
					a.Delete_Screen();
					a.file_reader();
					String input2 = scan3.nextLine();
					a.Delete_Screen();
				} else if (rkey == KeyEvent.VK_ENTER && px == 16) {
					System.exit(0);
				}
				if(px==10) {
					px+=6;
				}
				else if(px==18) {
					px-=6;
				}
				Game_Management.screen.getTextWindow().setCursorPosition(47, px);
				System.out.print("►");

				keypr = 0;
			}
			TimeUnit.MILLISECONDS.sleep(20);
			
		}
	}

	
	//Backup menu.
	
	/*public void menu() throws InterruptedException, IOException {
		a.file_reader_multi();
		while (true) {

			Game_Management.screen.getTextWindow().setCursorPosition(20, 2);
			System.out.print("  _    _ ______ _      _______   __   _____ _   _          _  ________ ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 3);
			System.out.print(" | |  | |  ____| |    |_   _\\ \\ / /  / ____| \\ | |   /\\   | |/ /  ____|");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 4);
			System.out.print(" | |__| | |__  | |      | |  \\ V /  | (___ |  \\| |  /  \\  | ' /| |__   ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 5);
			System.out.print(" |  __  |  __| | |      | |   > <    \\___ \\| . ` | / /\\ \\ |  < |  __|  ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 6);
			System.out.print(" | |  | | |____| |____ _| |_ / . \\   ____) | |\\  |/ ____ \\| . \\| |____ ");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 7);
			System.out.print(" |_|  |_|______|______|_____/_/ \\_\\ |_____/|_| \\_/_/    \\_\\_|\\_\\______|");

			Game_Management.screen.getTextWindow().setCursorPosition(20, 22);
			System.out.print("                      __    __    __    __");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 23);
			System.out.print("                     /  \\  /  \\  /  \\  /  \\");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 24);
			System.out.print("____________________/  __\\/  __\\/  __\\/  __\\_____________________________");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 25);
			System.out.print("___________________/  /__/  /__/  /__/  /________________________________");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 26);
			System.out.print("                   | / \\   / \\   / \\   / \\  \\____");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 27);
			System.out.print("                   |/   \\_/   \\_/   \\_/   \\    o \\");
			Game_Management.screen.getTextWindow().setCursorPosition(20, 28);
			System.out.print("                                           \\_____/--<");

			Game_Management.screen.getTextWindow().setCursorPosition(48, 10);
			System.out.print(" 1 - Start Game");
			Game_Management.screen.getTextWindow().setCursorPosition(48, 12);
			System.out.print(" 2 - Score Table");
			Game_Management.screen.getTextWindow().setCursorPosition(48, 14);
			System.out.print(" 3 - Exit");
			Game_Management.screen.getTextWindow().setCursorPosition(43, 18);
			System.out.print("Your choice : ");

			String input = scan.nextLine();

			if (input.equals("1")) {
				a.RESET();
				File music = new File("opening.wav");
				a.play_music_wav(music);
				a.game_loading();
				
				 //music=new File("super_mario64.wav"); play_music_wav(music);
				 
				b.moving();
				while (true) {
					Game_Management.screen.getTextWindow().setCursorPosition(48, 10);
					System.out.println("Game Over!!!");
					Game_Management.screen.getTextWindow().setCursorPosition(43, 18);
					System.out.print("Your name : ");
					Game_Management.player_name = scan2.nextLine();
					if (!Game_Management.player_name.contains(";")) {
						break;
					} else {
						Game_Management.screen.getTextWindow().setCursorPosition(46, 20);
						System.out.print("Don't use \";\".");
						TimeUnit.MILLISECONDS.sleep(1000);
					}
					a.Delete_Screen();
				}
				a.file_writer(Game_Management.player_name, Game_Management.score);
				a.Delete_Screen();
			} else if (input.equals("2")) {
				a.Delete_Screen();
				a.file_reader();
				String input2 = scan3.nextLine();
			} else if (input.equals("3")) {
				System.exit(0);
			} else {
				Game_Management.screen.getTextWindow().setCursorPosition(36, 20);
				System.out.print("Please only choose option between 1 and 3.");
				TimeUnit.MILLISECONDS.sleep(1000);
			}
			a.Delete_Screen();
			input = "0";
		}
	}*/
}
