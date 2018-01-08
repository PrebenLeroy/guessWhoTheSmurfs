package com.example.guesswhothesmurfs.persistency;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by prebe on 8/01/2018.
 */

public final class CharacterContract {

    //EERST schema aanmaken die database gaat declareren
    //added voor contentprovider
    public static final String CONTENT_AUTHORITY = "com.example.provider.guesswhothesmurfs  ";

    //added voor contentprovider
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    //added voor contentprovider
    // Path to get CLIENT APP to our table
    public static final String PATH_CHARACTERS = "characters";

    public static final class CharacterEntry implements BaseColumns {

        //added voor contentprovider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CHARACTERS);

        // Table Name
        public static final String TABLE_NAME = "smurfs";

        // Columns
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PICTUREID = "picture";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
    }

}
