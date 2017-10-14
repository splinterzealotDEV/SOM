import org.opencv.core.Mat;


/**
 * Created by taka on 10/14/2017.
 */
public class Funcionamiento {
    public Funcionamiento()
    {

    }
    public void pintarMatrizCentroides(Mat img,Colores[] cent)
    {
        int d,k=0,min=0;
        int rows = img.rows(); //Calculates number of rows
        int cols = img.cols(); //Calculates number of columns
        //System.out.println("la p: "+getN());
        //System.out.println("LAs filas: "+rows*cols);
        int ch = img.channels(); //Calculates number of channels (Grayscale: 1, RGB: 3, etc.)
        //adding all pixels to the list
        for (int i=0; i<rows; i++)
        {
            for (int j=0; j<cols; j++)
            {
                double[] data = img.get(i, j); //Stores element in an array
                Colores c=new Colores((int)data[2],(int)data[1],(int)data[0]);
                Aprendizaje a =new Aprendizaje();
                for(int t=0;t<cent.length;t++) {
                    d = a.distancia(c,cent[t]);
                    if (t == 0) {
                        min = d;
                        k = t;
                    }
                    //checking if the current distance is less than the previous
                    else if (d < min) {
                        min = d;
                        //store the centroid that is closer
                        k = t;
                    }
                    double[] pintar=new double[3];
                    pintar[2]=cent[k].getR();
                    pintar[1]=cent[k].getG();
                    pintar[0]=cent[k].getB();
                    img.put(i,j,pintar);

                }

            }

        }
    }
}
