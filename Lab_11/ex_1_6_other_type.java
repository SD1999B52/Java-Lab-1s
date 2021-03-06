import java.io.*;
import java.util.Scanner;

class ex_1_6_other_type {
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
			byte count = sc.nextByte();
			
			//Открыть файл одновременно для чтения и записи "rw"
			RandomAccessFile rf = new RandomAccessFile( f1, "rw" );
			System.out.println( "Исходный размер файла в байтах = " + rf.length() + ", указатель стоит на " + rf.getFilePointer() + " -м байте" );
			
			System.out.println( "Введите числа:" );
			for ( byte i = 0; i < count; i++ ) {
				rf.writeByte( sc.nextByte());
			}
			System.out.println( "Новый размер файла в байтах = " + rf.length() + ", указатель стоит на " + rf.getFilePointer() + " -м байте" );
			System.out.println( " количество байт на 1 число = " + rf.length() / count );
			rf.close();
			
			//открыть файл только для чтени¤ "r"
			rf = new RandomAccessFile( f1, "r" );
			//прочитать числа из файла и вывести на экран
			System.out.println( "\n числа в файле:" );
			for ( byte i = 0; i < count; i++ ) {
				rf.seek( i );
				System.out.println( "число" + i + ": " + rf.readByte());
			}
			
			rf.seek( rf.getFilePointer() - 1 );//перевод указателя на последнее число
			System.out.println( " Количество чисел в файле = " + rf.length() / 1 + ", последнее число = " + rf.readByte());
			
			//поиск заданного числа в файле и определение его номера (номеров)
			System.out.print( "\nВведите число, которое нужно найти в файле => " );
			byte x = sc.nextByte();
			byte kol = 0;//количество искомых чисел в файле
			for ( byte i = 0; i < count; i++ ) {
				rf.seek( i  );
				byte number = rf.readByte();
				if ( number == x ) {
					kol++;
					System.out.print( "номер " + i + ", " );
				}
			}
			System.out.println( " количество искомых чисел = " + kol );
			rf.close();
			
			//СОРТИРОВКА ЧИСЕЛ В ФАЙЛЕ МЕТОДОМ «ПУЗЫРЬКА»
			rf = new RandomAccessFile( f1, "rw" );//открыт дл¤ чтения и записи
			for ( byte k = 0; k < count; k++ ) {// k - номер просмотра
				for ( byte i = 0; i < count - k - 1; i++ ) {//i - номер числа
					rf.seek( i  );//переход к i-тому числу чтение i-того и (i+1)-го чисел в переменные
					byte number1 = rf.readByte();
					byte number2 = rf.readByte();
					if ( number1 > number2 ) {//условие для сортировки по возрастанию возврат указателя на i-тое число и перестановка (запись чисел в обратном порядке)
						rf.seek( i );
						rf.writeByte( number2 );
						rf.writeByte( number1 );
					}
				}
			}
			System.out.println( "\n Числа, отсортированные в файле:" );
			for ( byte i = 0; i < count; i++ ) {
				rf.seek( i );
				System.out.print( " " + rf.readByte());
			}
			rf.close();
		} catch ( IOException e ) {
			System.out.println( "End of file " + e );
		}
	}
}