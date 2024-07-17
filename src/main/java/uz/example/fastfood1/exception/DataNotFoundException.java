package uz.example.fastfood1.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }
}
