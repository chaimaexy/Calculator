package ma.ensa.calculator_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "calculator.bd";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_NOM = "nom";
    private static final String COLUMN_PRENOM = "prenom";
    private static final String COLUMN_Role = "role";
    private static final String COLUMN_OLDPASSWORD = "old_password";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LOGIN +  " TEXT, " +
                COLUMN_PASSWORD +  " TEXT, " +
                COLUMN_NOM +  " TEXT, " +
                COLUMN_PRENOM + " TEXT, " +
                COLUMN_Role + " TEXT, " +
                COLUMN_OLDPASSWORD +  " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addAuth(String login,String password,String nom, String prenom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_LOGIN, login);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_NOM, nom);
        cv.put(COLUMN_PRENOM, prenom);
        cv.put(COLUMN_Role, "");
        cv.put(COLUMN_OLDPASSWORD, "");
     

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Succes!", Toast.LENGTH_SHORT).show();
        }
    }

}
