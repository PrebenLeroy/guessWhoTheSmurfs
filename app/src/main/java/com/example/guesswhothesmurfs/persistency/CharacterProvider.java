package com.example.guesswhothesmurfs.persistency;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.example.guesswhothesmurfs.persistency.CharacterContract.CONTENT_AUTHORITY;
import static com.example.guesswhothesmurfs.persistency.CharacterContract.CharacterEntry.TABLE_NAME;
import static com.example.guesswhothesmurfs.persistency.CharacterContract.PATH_CHARACTERS;

/**
 * Created by prebe on 8/01/2018.
 */

public class CharacterProvider extends ContentProvider {

    private static final String TAG = CharacterProvider.class.getSimpleName();
    private CharacterDbHelper databaseHelper;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int CHARACTERS = 1; //whole table
    private static final int CHARACTERS_ID = 2;//row by id
    private static final int CHARACTER_CARACTER_NAME = 3; // rows by character

    static{
        //contains all URI patterns
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CHARACTERS, CHARACTERS);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CHARACTERS + "/#", CHARACTERS_ID);
        uriMatcher.addURI(CONTENT_AUTHORITY, PATH_CHARACTERS + "/*", CHARACTER_CARACTER_NAME);
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new CharacterDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor;

        switch (uriMatcher.match(uri)) {

            case CHARACTERS:
                cursor = database.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CHARACTERS_ID:
                selection = CharacterContract.CharacterEntry._ID + " = ?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                cursor = database.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException(TAG + "Unknown URI: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {

            case CHARACTERS:
                return insertRecord(uri, values, TABLE_NAME);
            default:
                throw new IllegalArgumentException(TAG + "Unknown URI: " + uri);
        }
    }

    private Uri insertRecord(Uri uri, ContentValues values, String tableName) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        long rowId = database.insert(tableName, null, values);

        if (rowId == -1) {
            Log.e(TAG, "Insert error for URI " + uri);
            return null;
        } else {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return ContentUris.withAppendedId(uri, rowId);

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {
            case CHARACTERS:			// To delete whole table
                return deleteRecord(uri, null, null, CharacterContract.CharacterEntry.TABLE_NAME);
            case CHARACTERS_ID:		// To delete a row by ID
                selection = CharacterContract.CharacterEntry._ID + " = ?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return deleteRecord(uri, selection, selectionArgs, CharacterContract.CharacterEntry.TABLE_NAME);
            case CHARACTER_CARACTER_NAME:	// To Delete a row by COUNTRY NAME
                selection = CharacterContract.CharacterEntry.COLUMN_NAME + " = ? ";
                selectionArgs = new String[] { String.valueOf(uri.getLastPathSegment()) };
                return deleteRecord(uri, selection, selectionArgs, CharacterContract.CharacterEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException(TAG + "Unknown URI: " + uri);
        }
    }

    private int deleteRecord(Uri uri, String selection, String[] selectionArgs, String tableName) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int rowsDeleted = database.delete(tableName, selection, selectionArgs);

        if (rowsDeleted != 0 ) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (uriMatcher.match(uri)) {

            case CHARACTERS:
                return updateRecord(uri, values, selection, selectionArgs, CharacterContract.CharacterEntry.TABLE_NAME);
            default:
                throw new IllegalArgumentException(TAG + "Unknown URI: " + uri);
        }
    }

    private int updateRecord(Uri uri, ContentValues values, String selection, String[] selectionArgs, String tableName) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        int rowsUpdated = database.update(tableName, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
