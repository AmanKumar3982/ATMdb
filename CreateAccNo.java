import java.util.Random;

public class CreateAccNo {
    public long generateAccountNumber() {
        Random random = new Random();
        long accountNumber = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return accountNumber;
    }
}