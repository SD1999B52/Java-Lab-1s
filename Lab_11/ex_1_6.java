import java.io.*;
import java.util.Scanner;

class ex_1_6 {
	public static void main( String[] args ) {
		try {
			File folder = new File( "My" );
			if ( folder.exists() == false ) {
				folder.mkdir();
			}
			
			File f1 = new File( "My/num1Mart.txt" );
			f1.createNewFile();
			
			Scanner sc = new Scanner( System.in, "cp1251" );
			
			System.out.print( "Сколько чисел надо записать в файл? \n => " );
			int count = sc.nextInt();
			
			//Открыть файл одновременно для чтения и записи "rw"
			RandomAccessFile rf = new RandomAccessFile( f1, "rw" );
			System.out.println( "Исходный размер файла в байтах = " + rf.length() + ", указатель стоит на " + rf.getFilePointer() + " -м байте" );
			
			System.out.println( "Введите числа:" );
			for ( int i = 0; i < count; i++ ) {
				rf.writeInt( sc.nextInt());
			}
			System.out.println( "Новый размер файла в байтах = " + rf.length() + ", указатель стоит на " + rf.getFilePointer() + " -м байте" );
			System.out.println( " количество байт на 1 число = " + rf.length() / count );
			rf.close();
			
			//открыть файл только для чтения "r"
			rf = new RandomAccessFile( f1, "r" );
			//прочитать числа из файла и вывести на экран
			System.out.println( "\n числа в файле:" );
			for ( int i = 0; i < count; i++ ) {
				rf.seek( i * 4 );
				System.out.println( "число" + i + ": " + rf.readInt());
			}
			
			rf.seek( rf.getFilePointer() - 4 );//перевод указателя на последнее число
			System.out.println( " количество чисел в файле = " + rf.length() / 4 + ", последнее число = " + rf.readInt());
			
			//поиск заданного числа в файле и определение его номера (номеров)
			System.out.print( "\nВведите число, которое нужно найти в файле => " );
			int x = sc.nextInt();
			int kol = 0;//количество искомых чисел в файле
			for ( int i = 0; i < count; i++ ) {
				rf.seek( i * 4 );
				int number = rf.readInt();
				if ( number == x ) {
					kol++;
					System.out.print( "номер " + i + ", " );
				}
			}
			System.out.println( " количество искомых чисел = " + kol );
			rf.close();
			
			//СОРТИРОВКА ЧИСЕЛ В ФАЙЛЕ МЕТОДОМ «ПУЗЫРЬКА»
			rf = new RandomAccessFile( f1, "rw" );//открыт для чтения и записи
			for ( int k = 0; k < count; k++ ) {// k - номер просмотра
				for ( int i = 0; i < count - k - 1; i++ ) {//i - номер числа
					rf.seek( i * 4 );//переход к i-тому числу чтение i-того и (i+1)-го чисел в переменные
					int number1 = rf.readInt();
					int number2 = rf.readInt();
					if ( number1 > number2 ) {//условие дл¤ сортировки по возрастанию возврат указател¤ на i-тое число и перестановка (запись чисел в обратном порядке)
						rf.seek( i * 4 );
						rf.writeInt( number2 );
						rf.writeInt( number1 );
					}
				}
			}
			System.out.println( "\n Числа, отсортированные в файле:" );
			for ( int i = 0; i < count; i++ ) {
				rf.seek( i * 4 );
				System.out.print( " " + rf.readInt());
			}
			rf.close();
		} catch ( IOException e ) {
			System.out.println( "End of file " + e );
		}
	}
}