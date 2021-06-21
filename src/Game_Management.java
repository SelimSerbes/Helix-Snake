import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.*;

import enigma.core.Enigma;

public class Game_Management {
	public int keypr;
	public int rkey;
	public KeyListener klis;
	public static enigma.console.Console screen = Enigma.getConsole("Window", 120, 30, 12,2);
	private static int height = 25;
	private static int weight = 60;
	public static char[][] game_area = new char[height + 4][weight + 4];
	public static char[][] game_area_backup = new char[height + 4][weight + 4];
	
	// Initial information
	static int snake_size = 3;
	static int score = 0;
	static String player_name = "";
	static double time = 0;
	static boolean flag_bait = false;
	static int px = height / 2, py = weight / 2;
	static int level = 0;
	static MultiLinkedList aminoacids = new MultiLinkedList();
	static boolean wall_flag = false;

	public void RESET() throws IOException {
		Delete_Screen();
		snake_size = 3;
		score = 0;
		time = 0;
		px = height / 2;
		py = weight / 2;
		level = 0;
		wall_flag = false;
		flag_bait = false;
		Snake.codon_count = 0;
		while (Snake.Snake_SLL.size() != 0) {
			Snake.Snake_SLL.removeFirst_char();
		}
		while (Snake.Snake_codon.size() != 0) {
			Snake.Snake_codon.removeFirst_char();
		}
		Initial();
	}

	public void game_loading() throws InterruptedException {
		for (int i = 1; i < 101; i++) {
			screen.getTextWindow().setCursorPosition(52, 14);
			System.out.print("Loading % " + i);
			TimeUnit.MILLISECONDS.sleep(40);
		}
		Delete_Screen();
	}
	
	public void play_music_wav(File music_wav) {
		try {
			Clip wav = AudioSystem.getClip();
			wav.open(AudioSystem.getAudioInputStream(music_wav));
			wav.start();
		} catch (Exception e) {
		}
	}

	public void Initial() throws IOException {
		for (int i = 0; i < game_area.length; i++) {
			for (int j = 0; j < game_area[i].length; j++) {
				game_area[i][j] = ' ';
				game_area_backup[i][j]=' ';
			}
		}
		Snake a = new Snake();
		a.Snake_random_initial();
		Map_random_initial();
	}

	public void file_reader() throws IOException {
		DoubleLinkedList top_10 = new DoubleLinkedList();
		top_10.addbetween(100000, "AHAHAHAHa");
		File file = new File("score_table.txt");
		if(!file.exists()) {
			FileWriter filewriter = new FileWriter(file, true);
		}
		FileReader fileReader = new FileReader(file);
		String line;
		BufferedReader br = new BufferedReader(fileReader);
		while ((line = br.readLine()) != null) {
			String[] score_table = line.split(";");
			top_10.addbetween(Integer.valueOf(score_table[1]), (String) score_table[0]);
		}
		br.close();
		top_10.new_display();
	}

	public void file_writer(String name, int score) {
		try {
			String text = name + ";" + score;
			File file = new File("score_table.txt");
			FileWriter filewriter = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(filewriter);
			writer.write(text);
			writer.newLine();
			writer.close();
		} catch (Exception hata) {
			hata.printStackTrace();
		}
	}

	public void file_reader_multi() throws IOException {
		FileReader fileReader = new FileReader("aminoacids.txt");
		String line;
		BufferedReader br = new BufferedReader(fileReader);
		while ((line = br.readLine()) != null) {
			String[] amino_acids = line.split(",");
			aminoacids.addCategory(amino_acids[0]);
			for (int i = 1; i < amino_acids.length; i++) {
				aminoacids.addItem(amino_acids[0], amino_acids[i]);
			}
		}
		br.close();
	}

	public void Delete_Screen() {
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 120; j++) {
				screen.getTextWindow().output(j, i, ' ');
			}
		}
	}

	public void Screen() {
		int time_temp = 0;
		time_temp = (int) time;
		if (time_temp % 20 == 0 && time_temp != 0 && wall_flag == false) {
			wall_one();
			level++;
			File music = new File("levelup.wav");
			play_music_wav(music);
			wall_flag = true;
		}
		if (time_temp % 20 == 1) {
			wall_flag = false;
		}
		for (int i = 1; i <= weight + 2; i++) {
			game_area[1][i] = '#';
			game_area_backup[1][i] = '#';
			game_area[height + 2][i] = '#';
			game_area_backup[height + 2][i] = '#';
		}
		for (int i = 1; i <= height + 2; i++) {
			game_area[i][1] = '#';
			game_area_backup[i][1] = '#';
			game_area[i][weight + 2] = '#';
			game_area_backup[i][weight + 2] = '#';
		}
		screen.getTextWindow().setCursorPosition(69, 0);
		System.out.print("SCORE : " + score);
		screen.getTextWindow().setCursorPosition(69, 1);
		System.out.print(" ----------");
		screen.getTextWindow().setCursorPosition(69, 25);
		System.out.print("Level: " + level);
		screen.getTextWindow().setCursorPosition(69, 26);
		System.out.print("Time: " + time_temp);
		Snake.Snake_SLL.delete_snake();
		Snake.Snake_SLL.printSnake();
		for (int i = 0; i < game_area.length; i++) {
			for (int j = 0; j < game_area[i].length; j++) {
				screen.getTextWindow().output(j, i, game_area[i][j]);
			}
		}
	}

	public void wall_one() {
		int x = 0, y = 0;
		while (true) {
			y = (int) (Math.random() * weight) + 2;
			x = (int) (Math.random() * height) + 2;
			if (game_area[x][y] != 'A' && game_area[x][y] != 'G' && game_area[x][y] != 'C' && game_area[x][y] != 'T'
					&& game_area[x][y] != '#') {
				break;
			}
		}
		game_area[x][y] = '#';
		game_area_backup[x][y] = '#';

	}

	public void Map_random_initial() {
		Snake a = new Snake();
		int x = 0, y = 0;
		char letter = '.';
		for (int i = 0; i < 3; i++) {
			while (true) {
				y = (int) (Math.random() * weight) + 2;
				x = (int) (Math.random() * height) + 2;
				letter = a.Random_letter();
				if (game_area[x][y] != 'A' && game_area[x][y] != 'G' && game_area[x][y] != 'C' && game_area[x][y] != 'T'
						&& game_area[x][y] != '#') {
					break;
				}
			}
			game_area[x][y] = letter;
			game_area_backup[x][y] = letter;
		}
	}

	public void Map_random_one() {
		Snake a = new Snake();
		int x = 0, y = 0;
		char letter = '.';
		while (true) {
			y = (int) (Math.random() * weight) + 2;
			x = (int) (Math.random() * height) + 2;
			letter = a.Random_letter();
			if (game_area[x][y] != 'A' && game_area[x][y] != 'G' && game_area[x][y] != 'C' && game_area[x][y] != 'T'
					&& game_area[x][y] != '#') {
				break;
			}
		}
		game_area[x][y] = letter;
		game_area_backup[x][y] = letter;
	}

}