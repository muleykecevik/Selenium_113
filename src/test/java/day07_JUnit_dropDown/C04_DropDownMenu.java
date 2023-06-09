package day07_JUnit_dropDown;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class C04_DropDownMenu {
    //● https://www.amazon.com/ adresine gidin.
    //- Test 1
    // Arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin
    //	-Test 2
    // 1. Kategori menusunden Books secenegini secin
    // 2. Arama kutusuna Java yazin ve aratin
    // 3. Bulunan sonuc sayisini yazdirin
    // 4. Sonucun Java kelimesini icerdigini test edin
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.amazon.com/");
    }
    @AfterClass
    public static void tearDown(){
        driver.close();

    }

    @Test
    public void test01(){
        //- Test 1
        // Arama kutusunun yanindaki kategori
        // menusundeki kategori sayisinin 45 oldugunu test edin

        //element dropdown oldugu icin select classindan
        // obje olursuturp kullaniriz

        WebElement dropDownMenuElement=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select= new Select(dropDownMenuElement);
        int expectedOptionCount=45;
        int actualOptionCount=select.getOptions().size();
        Assert.assertEquals(expectedOptionCount,actualOptionCount);
    }
     @Test
    public void test02(){
         WebElement dropDownMenuElement=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
         Select select= new Select(dropDownMenuElement);
         //	-Test 2
         // 1. Kategori menusunden Books secenegini secin
         select.selectByVisibleText("Books");
         // 2. Arama kutusuna Java yazin ve aratin
         WebElement aramaKutusu= driver.findElement(By.id("twotabsearchtextbox"));
         aramaKutusu.sendKeys("Java" + Keys.ENTER);
         // 3. Bulunan sonuc sayisini yazdirin
         WebElement sonucSayi=driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]"));
         System.out.println(sonucSayi.getText());
         // 4. Sonucun Java kelimesini icerdigini test edin
         String expectedIcreik="Java";
         String actualSonuc=sonucSayi.getText();
         Assert.assertTrue(actualSonuc.contains(expectedIcreik));




     }


}
