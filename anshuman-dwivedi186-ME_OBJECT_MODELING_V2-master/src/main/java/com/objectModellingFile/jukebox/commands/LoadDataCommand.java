package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand{

    private ISongService mSongService;

    public LoadDataCommand(ISongService mSongService) {
        this.mSongService = mSongService;
    }

    @Override
    public void execute(List<String> commands) {
        mSongService.loadPoolOfSongs(commands.get(1));
        System.out.println("Songs Loaded successfully");
    }
    
}
