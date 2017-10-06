import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

public class Utils extends FileFilter{


    public Utils()
    {

    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        } else {
            return f.getName().toLowerCase().endsWith(".jpg");
        }
    }

    @Override
    public String getDescription() {
        return "JPEG (*.jpg)";
    }


    public Mat leerImagen(String ruta)
    {
        Mat matriz=Imgcodecs.imread(ruta);
        return matriz;
    }

    /**
     * this method converts a Matrix to an image
     * @param m a Matrix to convert to image
     * @return an Image
     */
    public Image toBufferedImage(Mat m) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        return image;
    }

    /**
     * this method displays an image in a jframe and set it's title
     * @param title a string to use as the title
     * @param img an image to display in the frame
     */
    public void displayImage(String title, Image img)
    {
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame(title);
        JLabel lbl=new JLabel(icon);
        frame.add(lbl);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * this method print the RGB values of Color instances stored in an array
     * @param arr the array of colors
     */
    public void imprimircolores(Colores[] arr)
    {
        for(int i=0;i<arr.length;i++)
        {
            System.out.println(arr[i].getR()+" RED");
            System.out.println(arr[i].getB()+" BLUE");
            System.out.println(arr[i].getG()+" GREEN");
        }
    }

    /**
     * this method use a Jfilechooser to select a jpg image
     * @return string with the path to the image
     */
    public String seleccionarImagen()
    {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
        //FileNameExtensionFilter
       fc.addChoosableFileFilter(new Utils());
       fc.setAcceptAllFileFilterUsed(true);
        int result = fc.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fc.getSelectedFile();

        }
        return fc.getSelectedFile().getAbsolutePath().replace("\\","\\\\");
    }
}
