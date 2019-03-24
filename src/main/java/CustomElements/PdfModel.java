package CustomElements;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * PDF文書のページを表すクラス。
 *
 * @author toru
 */
public class PdfModel {
    private static final Logger logger = Logger.getLogger(PdfModel.class.getName());

    private PDDocument document;
    private PDFRenderer renderer;

    public PdfModel(Path path) {
        try {
            document = PDDocument.load(path.toFile());
            renderer = new PDFRenderer(document);
        } catch (IOException ex) {
            throw new UncheckedIOException("PDDocument throws IOException file=" + path, ex);
        }
    }

    public PdfModel(InputStream inputStream) {
        try {
            document = PDDocument.load(inputStream);
            renderer = new PDFRenderer(document);
        } catch (IOException ex) {
            throw new UncheckedIOException("PDDocument throws IOException file=" + inputStream.toString(), ex);
        }
    }



    public int numPages() {
        return document.getPages().getCount();
    }

    public Image getImage(int pageNumber) {
        BufferedImage pageImage;
        try {
            pageImage = renderer.renderImage(pageNumber);
        } catch (IOException ex) {
            throw new UncheckedIOException("PDFRenderer throws IOException", ex);
        }
        return SwingFXUtils.toFXImage(pageImage, null);
    }

    public void close(){
        try {
            document.close();
        } catch (IOException ex) {
            throw new UncheckedIOException("Cannot close document.", ex);
        }
    }
}