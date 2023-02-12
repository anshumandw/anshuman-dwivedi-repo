package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class DeletePlaylistCommand implements ICommand {

    private IPlaylistService mPlaylistService;

    public DeletePlaylistCommand(IPlaylistService mPlaylistService) {

        this.mPlaylistService = mPlaylistService;

    }

    @Override
    public void execute(List<String> commands) {
        mPlaylistService.delete(Integer.parseInt(commands.get(1)), Integer.parseInt(commands.get(2)));
        System.out.println("Delete Successful");       
    }
    
}
