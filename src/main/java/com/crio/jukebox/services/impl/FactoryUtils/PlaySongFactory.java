package com.crio.jukebox.services.impl.FactoryUtils;

import com.crio.jukebox.entities.PlayPlayList;

public class PlaySongFactory {

    private static final String NEXT = "NEXT";
    private static final String BACK = "BACK";

    public static String getPlaySongFactory(PlayPlayList playPlayList, String command) {

        switch (command) {

            case NEXT:

                return playPlayList.next();

            case BACK:

                return playPlayList.prev();

            default:

                return playPlayList.playWithCustomSongId(command);

        }



    }

}
