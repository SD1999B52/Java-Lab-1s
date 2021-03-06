public class example16_04 {
	public static void main( String[] args ) {
		try {
			//блок кода, вызывающего ошибку
			System.out.println( "0" );
			//брошено» исключение и создан экземпляр RuntimeException с сообщением
			throw new RuntimeException( "ошибка" );
		} catch ( NullPointerException e ) { //исключение перехвачено
			//обработчик исключений типа NullPointerException
			System.out.println( "1" ); //исключение обработано
		} catch ( Exception e ) { //исключение перехвачено
			//обработчик исключений типа Exception
			System.out.println( "2" ); //исключение обработано
		} catch ( Error e ) { //исключение перехвачено
			//обработчик исключений типа Error
			System.out.println( "3" ); //исключение обработано
		}
		System.out.println( "4" );
	}
}