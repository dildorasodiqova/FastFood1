package uz.example.fastfood1.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String str){
         super(str);
    }
}
