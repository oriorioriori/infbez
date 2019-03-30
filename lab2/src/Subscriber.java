public class Subscriber {
    String name;

    int P, K, q;
    private double X;
    double A, Y;
    double recievedY;
    String log;

    Subscriber(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setP(int p) throws Exception{
        for (int i = 2; i <= p/2; i++){
            int temp = p % i;
            if (temp == 0){
                this.log = ("P - не простое число!");
                throw new Exception("P - не простое число!");
            }
        }
        this.P = p;
        this.log = "P = " + p;

        this.q = (p - 1) / 2;
        for (int i = 2; i <= q/2; i++){
            int temp = q % i;
            if (temp == 0){
                this.log = ("q - не простое число (q = " + q + ")");
                throw new Exception("q - не простое число! (q = " + q + ")");
            }
        }

    }

    public void setA(double a) throws Exception{
        int p = this.P;
        if (((a > 1) && (a < p--)) && (Math.pow(a, (double) q) % P != 1)){
            this.A = a;
        } else {
            this.log = ("Условия выбора А не выполняются! ((1 < A < P-1) && (A^q mod P != 1)");
            throw new Exception("Условия выбора А не выполняются! ((1 < A < P-1) && (A^q mod P != 1)");
        }
        this.log = "A = " + this.A;
    }

    public void setX(double x){
        this.X = x;
        this.log = "X " + this.getName() + " = " + this.X;
        Main.logToFile(getLog());
    }

    public double getY() {
        return Y;
    }

    public void logY(){
        this.log = "Y " + this.getName() + " = "  +
                this.getA() + "^" + this.getX() + " mod " + this.getP() + " = " + this.getY();
    }

    public void logKey(){
        this.log = "K " + this.getName() + " = "  +
                this.getRecievedY() + "^" + this.getX() + " mod " + this.getP() + " = " + this.getK();
    }

    public int getP() {
        return P;
    }

    public double getX() {
        return X;
    }

    public double getA() {
        return A;
    }

    public void computeY(double a, double x, int p){
        Y = (int) Math.pow(a, x) % p;
        logY();
        Main.logToFile(getLog());
    }

    public void computeKey(double y, double x, int p){
        K = (int) Math.pow(y, x) % p;
        logKey();
        Main.logToFile(getLog());
    }

    public int getK(){
        return K;
    }

    public void recieveY(double y){
        this.recievedY = y;
        this.log = this.getName() +" получил Y = " + this.recievedY;
        Main.logToFile(getLog());
    }

    public double getRecievedY() {
        return recievedY;
    }

    public String getLog() {
        return log;
    }
}
