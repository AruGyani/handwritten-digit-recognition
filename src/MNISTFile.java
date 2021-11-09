import static java.lang.String.format;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MNISTFile {
    private final static int LABEL_MAGIC_INT = 2049;
    private final static int DATA_MAGIC_INT = 2051;
    
    public static List<Digit> loadImage(String prefix) {
        List<Digit> images = null;
        ClassLoader loader = MNISTFile.class.getClassLoader();
        

        String imgFileName = "data/" + prefix + "-images.idx3-ubyte";
        String labelFileName = "data/" + prefix + "-labels.idx1-ubyte";

        try (
            DataInputStream imageStream = new DataInputStream(loader.getResourceAsStream(imgFileName));
            DataInputStream labelStream = new DataInputStream(loader.getResourceAsStream(labelFileName));
        ) {
            if (imageStream.readInt() != DATA_MAGIC_INT)
                throw new IOException("Unknown file format for " + imgFileName);
            if (labelStream.readInt() != LABEL_MAGIC_INT)
                throw new IOException("Unknown file format for " + labelFileName);

            int nImages = imageStream.readInt();
            int nLabels = labelStream.readInt();

            if (nImages != nLabels)
                throw new IOException(format("File %s and %s contain data for a different number of images", imgFileName, labelFileName));
            
            images = new ArrayList<>(nImages);
            
            int rows = imageStream.readInt();
            int cols = imageStream.readInt();

            byte[] data = new byte[rows * cols];

            for (int i = 0; i < nImages; i++) {
                double[] img = new double[rows * cols];

                imageStream.read(data, 0, data.length);

                for (int d = 0; d < img.length; d++) {
                    img[d] = (data[d] & 255) / 255.0;
                }                

                images.add(new Digit(img, labelStream.readByte()));
            }
        } catch(IOException e) {  
            e.printStackTrace();
        }

        return images;
    }
}
