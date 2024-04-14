package com.crio.jukebox.appConfig;

import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.commands.Impl.CreatePlaylistCommand;
import com.crio.jukebox.commands.Impl.CreateUserCommand;
import com.crio.jukebox.commands.Impl.DeletePlaylistCommand;
import com.crio.jukebox.commands.Impl.LoadDataCommand;
import com.crio.jukebox.commands.Impl.ModifyPlaylistCommand;
import com.crio.jukebox.commands.Impl.PlayPlaylistCommand;
import com.crio.jukebox.commands.Impl.PlaySongCommand;
import com.crio.jukebox.repositories.IAlbumRepository;
import com.crio.jukebox.repositories.IArtistRepository;
import com.crio.jukebox.repositories.ISongPoolRepository;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.impl.AlbumRepositoryImpl;
import com.crio.jukebox.repositories.impl.ArtistRepositoryImpl;
import com.crio.jukebox.repositories.impl.SongPoolRepositoryImpl;
import com.crio.jukebox.repositories.impl.UserRepositoryImpl;
import com.crio.jukebox.services.IAlbumService;
import com.crio.jukebox.services.IArtistService;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.ISongPoolService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.impl.AlbumServiceImpl;
import com.crio.jukebox.services.impl.ArtistServiceImpl;
import com.crio.jukebox.services.impl.PlaylistServiceImpl;
import com.crio.jukebox.services.impl.SongPoolServiceImpl;
import com.crio.jukebox.services.impl.UserServiceImpl;

public class ApplicationConfig {

    private final CommandInvoker commandInvoker = new CommandInvoker();

    private final ISongPoolRepository songPoolRepository = new SongPoolRepositoryImpl();
    private final IArtistRepository artistRepository = new ArtistRepositoryImpl();
    private final IAlbumRepository albumRepository = new AlbumRepositoryImpl();
    private final IUserRepository userRepository = new UserRepositoryImpl();

    
    private final IArtistService artistService = new ArtistServiceImpl(artistRepository);
    private final IAlbumService albumService = new AlbumServiceImpl(albumRepository);
    private final ISongPoolService songPoolService = new SongPoolServiceImpl(songPoolRepository, artistRepository, albumService, artistService);
    private final IUserService userService = new UserServiceImpl(userRepository, songPoolService);
    private final IPlaylistService playlistService = new PlaylistServiceImpl(userService, userRepository, songPoolService);

    private final ICommand loadDataCommand = new LoadDataCommand(songPoolService);
    private final ICommand createUserCommand = new CreateUserCommand(userService);
    private final ICommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final ICommand deletePlaylistCommand = new DeletePlaylistCommand(playlistService);
    private final ICommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
    private final ICommand playPlaylistCommand = new PlayPlaylistCommand(playlistService);
    private final ICommand playSongCommand = new PlaySongCommand(userService);

    public CommandInvoker getCommandInvoker() {
        commandInvoker.addCommand("LOAD-DATA", loadDataCommand);
        commandInvoker.addCommand("CREATE-USER", createUserCommand);
        commandInvoker.addCommand("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.addCommand("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.addCommand("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.addCommand("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.addCommand("PLAY-SONG", playSongCommand);

        return commandInvoker;
    }


}
