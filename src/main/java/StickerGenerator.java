import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickerGenerator {

    void create(InputStream inputStream, String fileName) throws IOException {

        File outputDir = new File("Images/Output");

        //lê imagem direto a partir arquivo JSON via inputstream
        //InputStream inputStream = new FileInputStream(new File(inputDir+"/the_shawshank_redemption.jpg"));

        //lê a imagem e pega os pixels a partir da URL
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();

        //leitura da imagem
        BufferedImage originalImage = ImageIO.read(inputStream);

        //criar nova imagem com transparência e novo tamanho, em memória
        int width = originalImage.getWidth(); //largura
        int height = originalImage.getHeight(); //altura
        int newHeight = height + 200 ;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT); //cria uma nova imagem, em memória, com 200 pixels a mais na altura e transparência

        //copiar a imagem original para nova imagem , em memória
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();  //caneta
        graphics.drawImage(originalImage, 0, 0, null); //escreve a imagem antiga na imagem nova

        //Configuração da fonte
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 164);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);

        //escrever uma frase na nova imagem, em memória

        //DESAFIO: CENTRALIZAR O TEXTO
        //ajustar tamanho da fonte para ficar proporcional ao tamanho da imagem
        String text = "Top, Rogerinho!" ;

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle =  fontMetrics.getStringBounds(text, graphics);
        int textWidth = (int) rectangle.getWidth();
        int textPositionWidth = (width - textWidth) / 2;
        graphics.drawString(text, textPositionWidth, newHeight - 100);

        //escrever a nova imagem em um arquivo

        //verifica se o diretório "Output" existe, se não exisir será criado

        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        ImageIO.write(newImage, "png", new File(outputDir + "/" + fileName)); //cria a imagem em disco e grava na pasta indicada

    }

}
