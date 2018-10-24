package Domain;

public class Record {
    private static Integer serialNumber = 0;
    private Integer sum;
    private Account from;
    private Account to;

    public Record() {
        serialNumber += 1;
    }

    public Record(Account from, Account to, Integer sum) {
        serialNumber += 1;
        this.from = from;
        this.to = to;
        this.sum = sum;
    }

    public static Integer getSerialNumber() {
        return serialNumber;
    }

    public static void setSerialNumber(Integer serialNumber) {
        Record.serialNumber = serialNumber;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }


    @Override
    public String toString() {
        return "Record{" +
                "sum=" + sum +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
