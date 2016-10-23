import org.junit.Assert;
import org.junit.Test;

public class PointTest {


    @Test
    public void test1() {
        Point p1 = new Point(9, 9);
        Point p2 = new Point(5, 9);

        Assert.assertEquals(1, p1.compareTo(p2));
    }
}
