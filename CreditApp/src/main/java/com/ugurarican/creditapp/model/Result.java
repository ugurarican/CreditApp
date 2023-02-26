package com.ugurarican.creditapp.model;

public class Result {

    //proje geliştirilmek istenilirse yapılan işlemlerin doğruluğunu veya yanlışlığını mesaj ile
    // burada geri bildirim yapabiliriz
    private boolean success;

    private String message;

    public Result(boolean success){
        this.success = success;
    }

    public Result(boolean success, String message){
        this(success);
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }
    public String getMessage(){
        return this.message;
    }

}
