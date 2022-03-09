package home_works.lesson_7_Reflection;

public class TestedClassExample {

    @AfterSuite
    private void goodByeAll() {
        System.out.println("Выполнение метода с аннотацией @AfterSuite");
    }

    @Test(priority = 5)
    public int method2() {
        System.out.println("Выполнение метода с приоритетом 5..");
        return (int) (Math.random() * 100);
    }

    @Test(priority = 9)
    public void goDance(int f) {
        System.out.println("Выполнение метода с приоритетом 9..");
    }

    @BeforeSuite
    private static void helloMethod() {
        System.out.println("Выполнение метода с аннотацией @BeforeSuite");
    }

    @Test(priority = 5)
    public static String doBadThings() {
        System.out.println("Выполнение метода с приоритетом 5..");
        return "ha-ha-ha";
    }

    @Test(priority = 2)
    public void methodWithArgs(String s, int t) {
        System.out.println("Выполнение метода с приоритетом 2..");
    }

    @Test(priority = 1)
    public void m3() {
        System.out.println("Выполнение метода с приоритетом 1..");
    }
}
