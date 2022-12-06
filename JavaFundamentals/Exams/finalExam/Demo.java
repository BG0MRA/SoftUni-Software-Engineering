package finalExam;

public class Demo {


    public static void main(String[] args) {
       int a = 10;
       double b = 5.5;
       a += b;
        System.out.println(a);

        for (int i = 10; i > 3 ; i-=2) {
            System.out.printf("%d ",i);
            
        }

        System.out.println();
    printText("Java");
    }
    public static void printText(String text) {
        System.out.println("I love" + text);

    }

}
