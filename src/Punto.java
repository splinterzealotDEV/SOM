public class Punto {
    private int x=0;
    private int y=0;
    private int c=12;

    public int getC() {
        return c;
    }

    public Punto(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Punto(){
        this.x=0;
        this.y=0;
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
    public int[] calcularCentro(Punto[] p){
        int sumax=0;
        int sumay=0;
        int[] result=new int[2];
        for(int i=0;i<p.length;i++){
            //summation of x
            sumax+=p[i].getX();
            //summation of y
            sumay+=p[i].getY();
        }
        //getting average of x and y
        result[0]=sumax/p.length;
        result[1]=sumay/p.length;
        return result;
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
        centroide.setX(centroide.getX()+(centroide.getX()-punto.getX())/itera);
        centroide.setY(centroide.getY()+(centroide.getY()-punto.getY())/itera);
    }
    public void ponerCentroides(Punto[] centroides, Punto centro, int radio)
    {
        double theta=(2*Math.PI)/centroides.length;
        for(int i=0;i<centroides.length;i++){
            centroides[i].setX((int)(centro.getX()+(radio*Math.cos(theta*i))));
            centroides[i].setY((int)(centro.getY()+(radio*Math.sin(theta*i))));
        }
        
    }
    public Punto[] llenarPuntos(Punto[] p)
    {
        for(int i=0;i<p.length;i++){
            p[i]=new Punto((int)(Math.random()*500),(int)(Math.random()*500));
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

}
