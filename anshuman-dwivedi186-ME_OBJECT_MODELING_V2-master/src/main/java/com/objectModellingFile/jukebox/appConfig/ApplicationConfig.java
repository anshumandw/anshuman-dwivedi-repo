package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlaylistCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlaylistCommand;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.ISongRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.PlaylistRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.PlaylistService;
import com.crio.jukebox.services.SongService;
import com.crio.jukebox.services.UserService;

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
