import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class main {
    public static void main(String[] args) {
        //static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Utils u=new Utils();
        System.out.println(Core.VERSION);
        Mat mat= Imgcodecs.imread("C:\\Users\\taka\\Pictures\\Wallpaper\\Captura.jpg");
        Colores[] arr=new Colores[3];
         arr[0]=new Colores(29,120,255);
        arr[1]=new Colores(29,255,255);
        arr[2]=new Colores(255,255,255);
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
