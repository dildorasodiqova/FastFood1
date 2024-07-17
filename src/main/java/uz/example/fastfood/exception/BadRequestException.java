package uz.example.fastfood.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String str){
         super(str);
    }
}
