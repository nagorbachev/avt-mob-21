import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest extends MainClass{
MainClass Main = new MainClass();

@Before
public void textStartTest()
{
    int a = Main.getLocalNumber();
    System.out.println(a);
}

    @After
    public void textFinishTest()
    {
        System.out.println("Finish test");
    }

@Test
public void testGetLocalNumber()
    {
        int expected = 14;
        int a = Main.getLocalNumber();
                if(a != expected) {
                    Assert.fail("Число не 14!");}
                }

    }
