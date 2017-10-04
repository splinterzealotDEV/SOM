import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class main {
    public static void main(String[] args) {
        //static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);
        //Mat mat= Imgcodecs.imread("C:\\Users\\taka\\Pictures\\Wallpaper\\eagle_green.jpg");
        Colores[] arr=new Colores[3];
         arr[0]=new Colores(29,120,255);
        arr[1]=new Colores(29,255,255);
        arr[2]=new Colores(255,255,255);
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
        Aprendizaje a=new Aprendizaje();
        a.imprimirRespuesta(arr);
    }
}
