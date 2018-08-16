package SeleniumUtils;/*
 *While writing this code, only God and I know what it is.
 *And now only God knows it
 *____________________________________by xuwei
 */

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//通过测试工具打开浏览器，获取Xpath的图片并替换下来
//需要提前把tools中的lib以及外面的那个jar文件导入
public class Selenium2Example {

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Administrator\\Desktop\\selumn\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        //进入网页
        driver.get("https://www.baidu.com/");
        //通过xpath获取html元素
        WebElement ele = driver.findElement(By.xpath("//*[@id=\"lg\"]"));
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Get entire page screenshot 先截取全图
        BufferedImage fullImg= null;
        try {
            fullImg = ImageIO.read(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Get the location of element on the page  记录下元素所在的位置
        org.openqa.selenium.Point point= ele.getLocation();
        // Get width and height of the element  获取元素的宽高
        int eleWidth= ele.getSize().getWidth();
        int eleHeight= ele.getSize().getHeight();
        // Crop the entire page screenshot to get only element screenshot   剪切
        BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
        try {
            ImageIO.write(eleScreenshot, "png", screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Copy the element screenshot to disk
        File screenshotLocation= new File("C:\\Users\\Administrator\\Pictures\\Saved Pictures\\Capture001.png");
        try {
            FileUtils.copyFile(screenshot, screenshotLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
