import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;


public class Main {
    static StringBuilder logs = new StringBuilder();

    void diffie(Subscriber subA, Subscriber subB) throws Exception{
        subA.setP(11);
        subB.setP(subA.getP());
        Main.logToFile(subA.getLog());

        subA.setA(7);
        subB.setA(subA.getA());
        Main.logToFile(subA.getLog());

        subA.setX(3);
        subB.setX(9);

        Main.logToFile("Подсчитываем Y...");
        subA.computeY(subA.getA(), subA.getX(), subA.getP());
        subB.computeY(subB.getA(), subB.getX(), subB.getP());

        Main.logToFile("Пересылаем Y между абонентами...");
        subA.recieveY(subB.getY());
        subB.recieveY(subA.getY());

        Main.logToFile("Подсчитываем ключи...");
        subA.computeKey(subA.getRecievedY(), subA.getX(), subA.getP());
        subB.computeKey(subB.getRecievedY(), subB.getX(), subB.getP());

        Main.logToFile("Проверяем ключи на равность...") ;
            if (subA.getK() == subB.getK()){
                Main.logToFile("Все верно!");
            } else{
                Main.logToFile("Где-то ошибка");
            }

    }

    static void logToFile(String logString){
        logs.append(logString + "\n");
        try (FileWriter fin = new FileWriter("res/logs")){
            fin.write(logs.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите верхнию границу проверки простых чисел: ");
        int limit = scanner.nextInt();
        int number;
        BitSet primes = AtkinSieve.getPrimesUpTo(limit);
        // Вывод последовательности
        for (number = 2; number <= limit; ++number)
            if (primes.get(number))
                System.out.format("%d is prime\n", number);
        MillerRabin mr = new MillerRabin();
        long num = number;
        //число итераций
        int k = 3;
        //проверка на простое число
        boolean prime = mr.isPrime(num, k);
        if (prime);
        else;
        System.out.println();

        Main main = new Main();
        Subscriber subscriberA = new Subscriber("Абонент A");
        Subscriber subscriberB = new Subscriber("Абонент B");
        main.diffie(subscriberA, subscriberB);
    }


}
