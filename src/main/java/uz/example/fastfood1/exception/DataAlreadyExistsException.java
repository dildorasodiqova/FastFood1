package uz.example.fastfood1.exception;

public class DataAlreadyExistsException extends RuntimeException {
    public DataAlreadyExistsException(String msg) {
        super(msg);
    }
}
