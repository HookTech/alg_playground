package ps.philo.playground.play;

/**
 * philo
 * # 4/14/19
 */
public class LambdaTest {

	public static void main(String[] args) {
		//final如果不写的话，Lambda表达式会默认该变量为final
		final String string = "Hello ";

		HelloWorld test = (String message) -> System.out.println(message + "World!");
		test.print(string);
		test.print2();
		HelloWorld.print3("message");
	}

	@FunctionalInterface
	interface HelloWorld {
		void print(String str);

		default void print2() {
			System.out.println("print2");
		}

		static void print3(String message) {
			System.out.println(message);
		}
	}
}
