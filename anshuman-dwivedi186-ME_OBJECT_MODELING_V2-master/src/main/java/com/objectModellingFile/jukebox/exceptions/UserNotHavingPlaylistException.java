package com.objectModellingFile.jukebox.exceptions;

public class UserNotHavingPlaylistException extends RuntimeException{
    public UserNotHavingPlaylistException()
    {
     super();
    }
    public UserNotHavingPlaylistException(String msg)
    {
     super(msg);
    }
}
