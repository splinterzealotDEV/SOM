public class Colores {
    int R=0,G=0,B=0;
    public Colores(int r,int g, int b)
    {
        this.R=r;
        this.G=g;
        this.B=b;
    }
    public Colores()
    {
        this.R=0;
        this.G=0;
        this.B=0;
    }

    public void setR(int r) {
        R = r;
    }

    public void setG(int g) {
        G = g;
    }

    public void setB(int b) {
        B = b;
    }

    public int getR() {
        return R;
    }

    public int getG() {
        return G;
    }

    public int getB() {
        return B;
    }
}
