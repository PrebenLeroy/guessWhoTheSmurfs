package com.example.guesswhothesmurfs.persistency;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.guesswhothesmurfs.R;

/**
 * Created by prebe on 8/01/2018.
 */

public class CharacterDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "character.db";
    private static final int DATABASE_VERSION = 1;

    private final String SQL_CREATE_CHARACTER_TABLE
            = "CREATE TABLE " + CharacterContract.CharacterEntry.TABLE_NAME
            + " (" + CharacterContract.CharacterEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CharacterContract.CharacterEntry.COLUMN_PICTUREID + " INTEGER NOT NULL, "
            + CharacterContract.CharacterEntry.COLUMN_NAME + " TEXT NOT NULL, "
            + CharacterContract.CharacterEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL"
            + ");";

    private static final String SQL_DROP_CHARACTER_TABLE =
            "DROP TABLE IF EXISTS " + CharacterContract.CharacterEntry.TABLE_NAME;

    public CharacterDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CHARACTER_TABLE);

        ContentValues cv = new ContentValues();
        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.grote);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Grote Smurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Hij is leider van de Smurfen en de meest wijze van allemaal. Hij heeft veel gezag en de Smurfen zullen het zelden wagen hem tegen te spreken.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.bril);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Brilsmurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Brilsmurf kan eenvoudig van de andere Smurfen onderscheiden worden omdat hij een bril draagt, en een erg slecht zicht heeft wanneer dit niet het geval is.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.smurfin);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Smurfin");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Smurfin was de eerste vrouwelijke Smurf in het Smurfendorp. Gargamel wekte haar in dit verhaal tot leven om de Smurfen het leven zuur te maken.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.lol);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Lolsmurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Lolsmurf is een Smurf die nooit kan ophouden met grappen maken. Lolsmurfs favoriete grap is het uitdelen van cadeautjes die ontploffen in het gezicht van wie er een opent.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.hippe);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Hippe Smurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Hippe Smurf is een Smurf die altijd bezig is met zijn uiterlijk. Hij heeft meestal een bloem op zijn muts en draagt meestal een spiegel met zich mee.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.klungel);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Klungelsmurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Hij heeft een goedaardig karakter, maar doet vaak onhandig waardoor zijn hulp niet altijd geapprecieerd wordt. Hij valt al eens over zijn eigen voeten.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.smul);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Smulsmurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Leeft vooral om te eten. In de tekenfilmserie vormt Smulsmurf met Koksmurf één personage met de naam Smulsmurf.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.potige);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Potige Smurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Potige Smurf is makkelijk te herkennen aan het getatoeëerde hartje op zijn arm. Hij beoefent elke sport die er bestaat. Zijn favoriete sport is gewichtheffen.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

        cv.put(CharacterContract.CharacterEntry.COLUMN_PICTUREID, R.mipmap.knutsel);
        cv.put(CharacterContract.CharacterEntry.COLUMN_NAME, "Knutselsmurf");
        cv.put(CharacterContract.CharacterEntry.COLUMN_DESCRIPTION, "Knutselsmurf is een Smurf die veel dingen uitvindt. Zijn uitvindingen vallen vaak in verkeerde handen waarna ze meestal rampen uitlokken. Heeft meestal een potlood achter zijn oor.");
        db.insert(CharacterContract.CharacterEntry.TABLE_NAME, null,cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ideally we wouldn't want to delete all of our entries!
        db.execSQL(SQL_DROP_CHARACTER_TABLE);
        onCreate(db);	// Call to create a new db with upgraded schema and version
    }
}
