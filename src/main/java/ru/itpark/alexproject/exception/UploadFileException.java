package ru.itpark.alexproject.exception;

import java.io.IOException;

public class UploadFileException extends RuntimeException {
    public UploadFileException(IOException e) {
    }
}
