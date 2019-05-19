import com.fxpro.interview_task.WaterAmountCalculator;
import com.fxpro.interview_task.exception.AmountValidationException;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(DataProviderRunner.class)
public class WaterAmountCalculatorTest {

    private WaterAmountCalculator waterAmountCalculator = new WaterAmountCalculator();


    @Test(expected = AmountValidationException.class)
    @UseDataProvider("testWithInvalidLandscapeDataProvider")
    public void testWithInvalidLandscape(int[] landscape) {
        waterAmountCalculator.calculateAmount(landscape);
        //Exception should be thrown
        fail();
    }


    @Test
    @UseDataProvider("testWaterAmountCalculationDataProvider")
    public void testAmountCalculation(int[] landscape, long expectedWaterAmount){
        long actualAmount = waterAmountCalculator.calculateAmount(landscape);
        assertEquals(expectedWaterAmount, actualAmount);
    }



    @DataProvider
    public static Object[][] testWaterAmountCalculationDataProvider() {
        return new Object[][]{
                {new int[]{5,2,3,4,5,4,0,3,1}, 9},
                {new int[]{5,2,3,4,5,4,3,3,1}, 6},
                {new int[]{5,5,5,5,5,5,5,5,5}, 0}
        };
    }


    @DataProvider
    public static Object[][] testWithInvalidLandscapeDataProvider() {
        return new Object[][]{
                {new int[]{Integer.MAX_VALUE}},
                {new int[]{Integer.MIN_VALUE}}
        };
    }


}
