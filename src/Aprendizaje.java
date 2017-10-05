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

    Colores[] P,C;
    Mat imagen;

    public int getc() {
        return c;
    }
    public Colores[] getC()
    {
        return C;
    }


    public Aprendizaje(Mat i)
    {
        this.N=i.rows()*i.cols();
        this.M=Integer.parseInt(JOptionPane.showInputDialog("Ingresar numero de centroides"));
        this.P=new Colores[N];
        this.C=new Colores[M];
        this.imagen=i;
    }

    public int distancia(Colores p,Colores c)
    {
        int distancia=0;
        //System.out.println(p.getR()+"+:"+c.getR());
        distancia+=Math.abs(p.getR()-c.getR());
        distancia+=Math.abs(p.getG()-c.getG());
        distancia+=Math.abs(p.getB()-c.getB());
        return distancia;
    }
    public void acercar(Colores p, Colores c,int itera)
    {
        //acercando cada componente del centroide a el color correspondiente con la proporcion itera
        c.setR(c.getR()+((p.getR()-c.getR())/itera));
        c.setG(c.getG()+((p.getG()-c.getG())/itera));
        c.setB(c.getB()+((p.getB()-c.getB())/itera));
    }
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
    public void aprender()
    {
        int d=0,k=0,min=0;
        for (int c=2;c<getc();c++)
        {
            for(int i=0;i<N-1;i++)
            {
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
                    if (d<min)
                    {
                        min=d;
                        k=j;
                    }
                }
                //acercar
                acercar(P[i],C[k],c);
            }
        }
    }
    public void imprimirRespuesta(Colores[] res)
    {
        int filas=800/res.length;
        int col=600;
        Mat im=new Mat(800,600, CvType.CV_8UC3);
        int conti=1,contj=1;
        for(c=0;c<res.length;c++)
        {
            for (int i = c*filas; i < filas*conti; i++)
            {
                for (int j =0;j<col;j++)
                {
                    double data[]=im.get(i,j);
                    data[0]=res[c].getB();
                    data[1]=res[c].getG();
                    data[2]=res[c].getR();
                    im.put(i,j,data);
                }
                contj++;
            }
            conti++;
        }
        Utils u=new Utils();
        u.displayImage("Resultados",u.toBufferedImage(im));
    }

    public void setC(Colores[] c) {
        C = c;
    }

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
