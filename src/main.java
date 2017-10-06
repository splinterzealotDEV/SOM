import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class main {
    public static void main(String[] args) {
        //necessary to compile the code
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Utils u=new Utils();

        Mat mat= Imgcodecs.imread(u.seleccionarImagen());

        Aprendizaje a=new Aprendizaje(mat);

        a.llenarPuntos(mat);
        a.setC(a.llenarArrayRndom(a.getC()));
        u.imprimircolores(a.C);
        //System.out.println(a.C[0].G);
        //System.out.println("la M= "+a.getM());
        a.aprender();
        u.imprimircolores(a.C);
        a.imprimirRespuesta(a.getC());
        u.displayImage("Imagen",u.toBufferedImage(a.imagen));

    }
}
