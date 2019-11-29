import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest extends MainClass {
    MainClass Main = new MainClass();


    @Test
    public void testGetClassString() {
        String a = new MainClass().getClassString();
        int indexJava = a.indexOf("Hello");
        int indexJava1 = a.indexOf("hello");

        if (indexJava == - 1) {
            Assert.fail("Слово \"Hello\" не найдено.");
        } else {
            System.out.println("Слово \"Hello\" найдено в индексе" +indexJava);
        }

        if (indexJava1 == - 1) {
           Assert.fail("Слово \"hello\" не найдено.");
        } else {
            System.out.println("Слово \"hello\" найдено в индексе" +indexJava1);
        }

    }

}

