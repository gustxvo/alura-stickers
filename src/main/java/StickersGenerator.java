import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class StickersGenerator {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void create(InputStream inputStream, String fileName) throws Exception {

        BufferedImage originalImage = ImageIO.read(inputStream);

        // create new image
        var width = originalImage.getWidth();
        var height = originalImage.getHeight();
        var newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        graphics.drawString("TOPZERA", 150 , newHeight - 80);

        File dir = new File("src/main/output");
        if (!dir.exists()) dir.mkdir();

        ImageIO.write(newImage, "png", new File(dir + "/" + fileName));
    }
}
