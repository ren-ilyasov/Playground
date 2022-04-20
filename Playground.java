package playground;


import java.util.Scanner;

public class Playground {

	final static float[] ssi = {(float) 0, (float) 0, (float) 0.51, (float) 0.9, (float) 1.12, (float) 1.24, (float) 1.32};
	
	static Scanner scan = new Scanner(System.in); // Ввод из консоли
	
	static String name; // Название таблицы
	
	static int c = 0; // Количество критериев

	static int v = 0; // Количество вариантов

	
	
	static float[][] v_table; // Таблица основных данных
	
	
	static int cell_size = 4;
	static int width = 0;
	static int height = 0;
	static String[][] table; // Конечная таблица
	
	public static void main(String[] args) {
		print("Введите название -> ");
		name = scan.nextLine();
		
		print("Введите количество критериев -> ");
		c = readInt();
		
		
		print("Введите количество вариантов -> ");
		v = readInt();
		
		v_table = new float[c][v];
		
		height = c + 2;
		width = v + 1;
		table = new String[height][width];
		
		table[height-1][0] = "КС"; // Коэффициент связи
		
		println("Введите критерии");
		for (int i = 1; i < c + 1; i++) {
			table[i][0] = scan.nextLine();
			if (cell_size < table[i][0].length()) {
				cell_size = table[i][0].length();
			}
		}
		
		println("Введите варианты");
		for (int i = 1; i < width; i++) {
			table[0][i] = scan.nextLine();
			if (cell_size < table[0][i].length()) {
				cell_size = table[0][i].length();
			}
		}

		// Ввод оценок критериев
		float c_sum = (float) 0.0;

		println("Оценки критериев и вариантов");
		for (int i = 0; i < c; i++) {
			System.out.printf("Критерий: %s\n", table[i+1][0]);
			print("Оценка -> ");
			float c_m = Float.valueOf(scan.nextLine());
			c_sum += c_m;
			
			// Ввод оценок вариантов
			float v_sum = (float) 0.0;
			for (int j = 0; j < v; j++) {
				System.out.printf("Вариант: %s\n", table[0][j+1]);
				print("Оценка -> ");
				v_table[i][j] = Float.valueOf(scan.nextLine());
				v_sum += v_table[i][j];
				v_table[i][j] *= c_m;
			}
			
			// Нормирование оценок вариантов
			for (int j = 0; j < v; j++) {
				v_table[i][j] /= v_sum;
			}
			
			println("");
		}
		
		
		for (int j = 0; j < v; j++) {
			float k = (float) 0.0;
			
			for (int i = 0; i < c; i++) {
				v_table[i][j] /= c_sum;
				table[i+1][j+1] = f_format(v_table[i][j]);
				k += v_table[i][j];
			}
			
			table[height-1][j+1] = f_format(k);
		}
	
		
		printTable();
		
	}
	
	// Ввод числа из консоли, при ошибке заново запрашивает ввод
	static int readInt() {
		while (true) {
			try {
				int value = Integer.valueOf(scan.nextLine());
				return value;
			}
			catch(Exception e) {
				println("Ошибка. Введите снова.");
			}
		}
	}

	static String align(String str, int size) {
		int addition = size - str.length();
		for (int i = 0; i < addition; i++) {
			if (i % 2 == 0) {
				str += " ";
			} else {
				str = " " + str;
			}
		}
		return str;
	}
	
	// Форматирует дробное число, и переводит в строку
	static String f_format(float f) {
		return String.format("%.2f", f);
	}
	
	// Вывод в консоль
	static void print(String str) {
		System.out.print(str);
	}
	
	// Вывод в консоль
	public static void println(String str) {
		System.out.println(str);
	}
	
	// Вывод таблицы
	static void printTable() {
		int line_size = width * (cell_size + 1) + 1;

		String str = "-";

		String repeated = new String(new char[line_size]).replace("\0", str);

		System.out.println(repeated);
		
		for (int i = 0; i < height; i++) {
			println(repeated);
			for (int j = 0; j < width; j++) {
				if (table[i][j]==null) {
					table[i][j]="";
				}
				String cell = "|" + align(table[i][j], cell_size);
				print(cell);
			}
			println("|");
		}
	
		println(repeated);
	}
}