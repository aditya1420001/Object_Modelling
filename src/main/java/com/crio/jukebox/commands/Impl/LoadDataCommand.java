package com.crio.jukebox.commands.Impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import com.crio.jukebox.commands.ICommand;
import com.crio.jukebox.services.ISongPoolService;

public class LoadDataCommand implements ICommand {


    private final ISongPoolService songPoolService;

    public LoadDataCommand(ISongPoolService songPoolServiceImpl) {
        this.songPoolService = songPoolServiceImpl;
    }


    @Override
    public void execute(List<String> tokens) {

        String csv = tokens.get(1);

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(csv));
            String line = reader.readLine();

            while (Objects.nonNull(line)) {
                List<String> commands = Arrays.asList(line.split(","));

                String songName = commands.get(1);
                String genre = commands.get(2);
                String albumName = commands.get(3);
                String albumArtist = commands.get(4);
                List<String> feauturedArtists = Arrays.asList(commands.get(5).split("#"));

                songPoolService.loadSong(songName, genre, albumName, albumArtist, feauturedArtists);

                line = reader.readLine();
            }
            reader.close();
            System.out.println("Songs Loaded successfully");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
