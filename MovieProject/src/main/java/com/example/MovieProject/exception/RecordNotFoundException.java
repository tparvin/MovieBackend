package com.example.MovieProject.exception;

public class RecordNotFoundException extends RuntimeException
{
    public RecordNotFoundException(String message)
    {
        super(message);
    }
}
