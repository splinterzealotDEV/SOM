import org.opencv.core.CvType;
import org.opencv.core.Mat;

import javax.swing.*;

public class Aprendizaje {
    private int N=0,M=2,c=13;

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }
//Color type array
    Colores[] P,C;
    //Matrix to store image
    Mat imagen;

    public int getc() {
        return c;
    }
    public Colores[] getC()
    {
        return C;
    }

    /**
     * constructor, gets row and col number from the input image and sets N(number of pixels) M(number of centroids)
     * Initialize two arrays to store pixel and centroids
     * @param i a Mat that stores an image
     */
    public Aprendizaje(Mat i)
    {
        this.N=i.rows()*i.cols();
        this.M=Integer.parseInt(JOptionPane.showInputDialog("Ingresar numero de centroides"));
        this.P=new Colores[N];
        this.C=new Colores[M];
        this.imagen=i;
    }

    /**
     * this method calculates the distance(metropoli) using RGB values
     * @param p a pixel
     * @param c a centroid
     * @return the distance between the RGB values of the pixel and centroid
     */
    public int distancia(Colores p,Colores c)
    {
        int distancia=0;
        //System.out.println(p.getR()+"+:"+c.getR());
        distancia+=Math.abs(p.getR()-c.getR());
        distancia+=Math.abs(p.getG()-c.getG());
        distancia+=Math.abs(p.getB()-c.getB());
        return distancia;
    }

    /**
     *this method pull the a centroid to a pixel using the RGB values
     * @param p a pixel
     * @param c a centroid
     * @param itera the divider value according the iteration on the training process
     */
    public void acercar(Colores p, Colores c,int itera)
    {
        //acercando cada componente del centroide a el color correspondiente con la proporcion itera
        c.setR(c.getR()+((p.getR()-c.getR())/itera));
        c.setG(c.getG()+((p.getG()-c.getG())/itera));
        c.setB(c.getB()+((p.getB()-c.getB())/itera));
    }

    /**
     * this method sweep the image and get the RGB values of each pixel and store them in the P array
     * @param img image to sweep
     */
    public void llenarPuntos(Mat img)
    {
        int cont=0;
        int rows = img.rows(); //Calculates number of rows
        int cols = img.cols(); //Calculates number of columns
        System.out.println("la p: "+getN());
        System.out.println("LAs filas: "+rows*cols);
        int ch = img.channels(); //Calculates number of channels (Grayscale: 1, RGB: 3, etc.)

        for (int i=0; i<rows; i++)
        {
            for (int j=0; j<cols; j++)
            {
                double[] data = img.get(i, j); //Stores element in an array

                //porque mlp open cv decodifica BGR
                //almacenando valores RGB de cada punto segun la imagen
                this.P[cont]=new Colores((int)data[2],(int)data[1],(int)data[0]);
                //System.out.println(cont);
                //img.put(i, j, data); //Puts element back into matrix
            cont++;
            }

        }
    }

    /**
     * this method perform the training process
     */
    public void aprender()
    {
        int d=0,k=0,min=0;
        //each iteration reduce the pull "strength"
        for (int c=2;c<getc();c++)
        {
            //iterating all the pixels stored in the P array
            for(int i=0;i<N;i++)
            {
                //iterating all the centroids
                for(int j=0;j<M;j++)
                {
                    //System.out.println("la j"+j);
                    //System.out.println("lap"+P.length);
                    //System.out.println("lac"+C.length);
                    d=distancia(P[i],C[j]);
                    if(j==0)
                    {
                        min = d;
                        k=j;
                    }
                    //checking if the current distance is less than the previous
                    else if (d<min)
                    {
                        min=d;
                        //store the centroid that is closer
                        k=j;
                    }
                }
                //pulling the centroid
                acercar(P[i],C[k],c);
            }
        }
    }

    /**
     * this method print a matrix to view the resulting colors of the centroids in a 800*600 image
     * dividing the image in rows according to the centroids array length
     * @param res centroids array
     */
    public void imprimirRespuesta(Colores[] res)
    {

        int filas=800/res.length;//setting each row length
        int col=600;
        //setting matrix dimensions and the type to store three dimensions RGB
        Mat im=new Mat(800,600, CvType.CV_8UC3);
        //counter to control where the next row colors starts
        int conti=1;
        //loop to control which row is being drawn
        for(c=0;c<res.length;c++)
        {
            for (int i = c*filas; i < filas*conti; i++)
            {
                for (int j =0;j<col;j++)
                {
                    //opencv store values in alphabetical order BGR
                    double data[]=im.get(i,j);
                    data[0]=res[c].getB();
                    data[1]=res[c].getG();
                    data[2]=res[c].getR();
                    im.put(i,j,data);
                }

            }
            //increase the control counter
            conti++;
        }
        Utils u=new Utils();
        //displaying the image
        u.displayImage("Centroides",u.toBufferedImage(im));
    }

    public void setC(Colores[] c) {
        C = c;
    }

    /**
     * this method fills an array of Colors with randow RGB values
     * @param a the Colors array
     * @return a filled array with random values
     */
    public Colores[] llenarArrayRndom(Colores[] a)
    {
        Colores[] b=a;
        for(int i=0;i<getM();i++)
        {

            //System.out.println(i);
            b[i]=new Colores((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));

        }
        return b;

    }



}
