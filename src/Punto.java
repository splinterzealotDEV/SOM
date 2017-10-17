public class Punto {
    private int x=0;
    private int y=0;
    public Punto(int x,int y){
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
        double x=a.getX()-b.getX();
        double y=a.getY()-b.getY();
        return (int)Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

}
