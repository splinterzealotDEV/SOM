import java.util.ArrayList;
import java.util.List;

public class Punto {
    private int x=0;
    private int y=0;
    private int c=12;
    static int id =0;
    private int sid=0;

    public int getSid() {
        return sid;
    }

    public static int getId() {
        return id;
    }

    public int getC() {
        return c;
    }

    public Punto(int x, int y){
        this.x=x;
        this.y=y;
        this.sid=id;
        id++;
    }
    public Punto(){
        this.x=0;
        this.y=0;
        this.sid=id;
        id++;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    /**
     *
     * @param p array of 2D points with X and Y coordinates
     * @return an array of size positions where the first one contains the x average and the second one contains the y average
     */
    public Punto calcularCentro(Punto[] p){
        int sumax=0;
        int sumay=0;

        //int[] result=new int[2];
        for(int i=0;i<p.length;i++){
            //summation of x
            sumax+=p[i].getX();
            //summation of y
            sumay+=p[i].getY();
        }
        //getting average of x and y
        //result[0]=sumax/p.length;
        //result[1]=sumay/p.length;
        Punto resultado=new Punto(sumax/p.length,sumay/p.length);

        return resultado;
    }
    public int calcularRadio(Punto p,Punto[] arr){
        int distancia=0;
        int lejano=0;
        int cercano=0;
        Punto pmenor=new Punto();
        Punto pmayor= new Punto();
        for(int i=0;i<arr.length;i++){
            distancia=calcularDistancia(p,arr[i]);
            if(i==0)
            {
                lejano=distancia;
                cercano=distancia;
            }
            else
            {
                if(distancia>lejano)
                {
                    lejano=distancia;
                    pmayor=arr[i];
                }
                if(distancia<cercano)
                {
                    cercano=distancia;
                    pmenor=arr[i];
                }
            }
        }
        return calcularDistancia(pmayor,pmenor)/2;
    }
    public int calcularDistancia(Punto a,Punto b){
        double x=Math.abs(a.getX()-b.getX());
        double y=Math.abs(a.getY()-b.getY());
        return (int)(x+y);

    }
    public void acercar(Punto centroide,Punto punto,int itera){
        //System.out.println(centroide.getX());
        //System.out.println("P: "+(centroide.getX()+((centroide.getX()-punto.getX())/itera)));
        centroide.setX(centroide.getX()+((centroide.getX()-punto.getX())/itera));
        centroide.setY(centroide.getY()+((centroide.getY()-punto.getY())/itera));
        //System.out.println(centroide.getX());
    }
    public Punto[] ponerCentroides(Punto[] centroides, Punto centro, int radio)
    {
        double theta=(2*Math.PI)/centroides.length;
        for(int i=0;i<centroides.length;i++){
            centroides[i].setX((int)(centro.getX()+(radio*Math.cos(theta*i))));
            centroides[i].setY((int)(centro.getY()+(radio*Math.sin(theta*i))));
        }
        return centroides;
    }
    public Punto[] llenarPuntos(Punto[] p)
    {
        for(int i=0;i<p.length;i++){
            p[i]=new Punto((int)(Math.random()*200),(int)(Math.random()*200));
        }
        return p;
    }
    public void imprimir(Punto[] p)
    {
        for(int i=0;i<p.length;i++){
            System.out.println(i+"X: ,"+p[i].getX()+"Y: "+p[i].getY());
        }
    }
    public void aprender(Punto[] P,Punto[] C)
    {
        int d=0,k=0,min=0;
        //each iteration reduce the pull "strength"
        for (int c=2;c<getC();c++)
        {
            //iterating all the pixels stored in the P array
            for(int i=0;i<P.length;i++)
            {
                //iterating all the centroids
                for(int j=0;j<C.length;j++)
                {

                    d=calcularDistancia(P[i],C[j]);
                    if(j==0)
                    {
                        min = d;
                        //System.out.println("el minimo1: "+min);
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
               // System.out.println("acercando centroide : "+k);
                acercar(C[k],P[i],c);
            }
        }
    }
    public void encontrarRuta(Punto[] centroids)
    {
        int d=0,min=0,index=0,cont=0,aux=0;
        Punto temp=new Punto();
        List<Punto> list=new ArrayList<Punto>();
        for(Punto p:centroids){
            list.add(p);
        }
        while(!list.isEmpty())
        {
            if(cont==0)
            {
                temp=list.get(cont);
                list.remove(cont);
            }
            for(int i=0;i<list.size();i++)
            {
                d=calcularDistancia(temp,list.get(i));
                if(i==0)
                {
                    min=d;
                    index=i;
                }
                else if(d<min)
                {
                    min=d;
                    index=i;
                }
            }

            System.out.println(temp.getSid()+","+list.get(index).getSid());
            aux=index;
            temp=list.get(index);
            list.remove(index);
            cont++;
        }
    }

}
