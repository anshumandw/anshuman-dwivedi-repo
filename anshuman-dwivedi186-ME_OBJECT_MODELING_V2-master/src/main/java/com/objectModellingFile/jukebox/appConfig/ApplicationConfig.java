package com.objectModellingFile.jukebox.appConfig;

import com.objectModellingFile.jukebox.commands.CommandInvoker;
import com.objectModellingFile.jukebox.commands.CreatePlaylistCommand;
import com.objectModellingFile.jukebox.commands.CreateUserCommand;
import com.objectModellingFile.jukebox.commands.DeletePlaylistCommand;
import com.objectModellingFile.jukebox.commands.LoadDataCommand;
import com.objectModellingFile.jukebox.commands.ModifyPlaylistCommand;
import com.objectModellingFile.jukebox.commands.PlayPlaylistCommand;
import com.objectModellingFile.jukebox.commands.PlaySongCommand;
import com.objectModellingFile.jukebox.repositories.IPlaylistRepository;
import com.objectModellingFile.jukebox.repositories.ISongRepository;
import com.objectModellingFile.jukebox.repositories.IUserRepository;
import com.objectModellingFile.jukebox.repositories.PlaylistRepository;
import com.objectModellingFile.jukebox.repositories.SongRepository;
import com.objectModellingFile.jukebox.repositories.UserRepository;
import com.objectModellingFile.jukebox.services.IPlaylistService;
import com.objectModellingFile.jukebox.services.ISongService;
import com.objectModellingFile.jukebox.services.IUserService;
import com.objectModellingFile.jukebox.services.PlaylistService;
import com.objectModellingFile.jukebox.services.SongService;
import com.objectModellingFile.jukebox.services.UserService;

public class ApplicationConfig {

    private final ISongRepository mSongRepository = new SongRepository();
    private final IUserRepository mUserRepository = new UserRepository();
    private final IPlaylistRepository mPlaylistRepository = new PlaylistRepository();

    private final ISongService mSongService = new SongService(mSongRepository, mPlaylistRepository);
    private final IUserService mUserService = new UserService(mUserRepository, mPlaylistRepository);
    private final IPlaylistService mPlaylistService = new PlaylistService(mPlaylistRepository, mSongRepository, mUserRepository, mUserService);
    
    private final LoadDataCommand mLoadDataCommand = new LoadDataCommand(mSongService);
    private final CreateUserCommand mCreateUserCommand = new CreateUserCommand(mUserService);
    private final CreatePlaylistCommand mCreatePlaylistCommand = new CreatePlaylistCommand(mPlaylistService);
    private final DeletePlaylistCommand mDeletePlaylistCommand = new DeletePlaylistCommand(mPlaylistService);
    private final PlayPlaylistCommand mPlayPlaylistCommand = new PlayPlaylistCommand(mSongService);
    private final ModifyPlaylistCommand mModifyPlaylistCommand = new ModifyPlaylistCommand(mPlaylistService);
    private final PlaySongCommand mPlaySongCommand = new PlaySongCommand(mSongService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA", mLoadDataCommand);
        commandInvoker.register("CREATE-USER", mCreateUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", mCreatePlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", mDeletePlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", mPlayPlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", mModifyPlaylistCommand);
        commandInvoker.register("PLAY-SONG", mPlaySongCommand);
        return commandInvoker;
    }
}
