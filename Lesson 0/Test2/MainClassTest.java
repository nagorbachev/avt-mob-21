import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest extends MainClass{
MainClass Main = new MainClass();

@Before
public void textStartTest()
{
    int a = Main.getClass_number();
    System.out.println(Main.getClass_number());
}

@Test
public void testGetClassNumber()
    {
       int a = Main.getClass_number();

       if (a < 45) {
           System.out.println("Ошибка! Число меньше 45!");
       } else {
           System.out.println("Правильно!");
       }

    }

    }
