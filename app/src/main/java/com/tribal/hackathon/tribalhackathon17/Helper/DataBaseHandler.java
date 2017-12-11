package com.tribal.hackathon.tribalhackathon17.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Departments;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Schemes;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SearchResult;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String TABLE_INITIAL = "initial_table";
    private static final String UNIQUE_ID = "_id";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LocalDatabase";
    private static final String UPPER_NODE_ID = "upper_node";
    private static final String PLACE_TYPE = "type";
    private static final String TABLE_PLACES = "places";
    private static final String ALLOCATED = "allocated";
    private String PLACE_NAME_OR_SCHEME_NAME = "name";
    private String TABLE_SCHEME = "scheme";
    private String SCHEME_NAME = "name";
    private String DEPTNAME = "dept";
    private String SCH_ID = "id";
    private String TABLE_DEPARTMENT = "departments";
    private String TABLE_TEMP_SCHEME = "temp_scheme";
    private String USED = "used";



    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INITIAL_TABLE = "CREATE TABLE " + TABLE_INITIAL + "("
                + UNIQUE_ID + " TEXT," + PLACE_NAME_OR_SCHEME_NAME + " TEXT)";
        db.execSQL(CREATE_INITIAL_TABLE);
        String CREATE_SCHEME_TABLE = "CREATE TABLE " + TABLE_SCHEME + "("
                + SCH_ID + " TEXT ," + SCHEME_NAME + " TEXT ," + DEPTNAME + " TEXT)";
        db.execSQL(CREATE_SCHEME_TABLE);
        String CREATE_PLACE_TABLE = "CREATE TABLE " + TABLE_PLACES + "("
                + SCH_ID + " TEXT ," + SCHEME_NAME + " TEXT ," + PLACE_TYPE + " TEXT ," + UPPER_NODE_ID + " TEXT)";
        db.execSQL(CREATE_PLACE_TABLE);
        String CREATE_DEPARTMENT_TABLE = "CREATE TABLE " + TABLE_DEPARTMENT + "("
                + SCH_ID + " TEXT ," + SCHEME_NAME + " TEXT)";
        db.execSQL(CREATE_DEPARTMENT_TABLE);

        String CREATE_TEMP_SCHEME_TABLE = "CREATE TABLE " + TABLE_TEMP_SCHEME + "("
                + SCH_ID + " TEXT ," + SCHEME_NAME + " TEXT ," + ALLOCATED + " INTEGER," + USED + " INTEGER)";
        db.execSQL(CREATE_TEMP_SCHEME_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INITIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
        onCreate(db);
    }

    public void addInitialEntries(List<Schemes.SchemeData> schemes, List<Places.Place> places) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < schemes.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(UNIQUE_ID, "S" + schemes.get(i).getId());
            values.put(PLACE_NAME_OR_SCHEME_NAME, schemes.get(i).getName());
            db.insert(TABLE_INITIAL, null, values);
        }
        for (int i = 0; i < places.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(UNIQUE_ID, "P" + places.get(i).getId());
            values.put(PLACE_NAME_OR_SCHEME_NAME, places.get(i).getName());
            db.insert(TABLE_INITIAL, null, values);
        }
        db.close();
    }

    public void addtempSchemes(List<SearchResult.allocation> schemes) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TEMP_SCHEME);
        for (int i = 0; i < schemes.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(SCH_ID, schemes.get(i).getId());
            values.put(SCHEME_NAME, schemes.get(i).getScheme_name());
            values.put(ALLOCATED, schemes.get(i).getAllocated_amount());
            values.put(USED, schemes.get(i).getUsed_amount());
            db.insert(TABLE_TEMP_SCHEME, null, values);
        }
        db.close();
    }

    public List<SearchResult.allocation> getTempScheme() {
        ArrayList<SearchResult.allocation> schemeList = new ArrayList<SearchResult.allocation>();
        String selectQuery = "SELECT  * FROM " + TABLE_TEMP_SCHEME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                schemeList.add(new SearchResult.allocation(cursor.getString(0), cursor.getString(1), cursor.getLong(2), cursor.getLong(3)));
            } while (cursor.moveToNext());
        }
        return schemeList;
    }

    public void addSchemes(List<Schemes.SchemeData> schemes) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SCHEME);
        for (int i = 0; i < schemes.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(SCH_ID, schemes.get(i).getId());
            values.put(SCHEME_NAME, schemes.get(i).getName());
            values.put(DEPTNAME, schemes.get(i).getDept_name());
            db.insert(TABLE_SCHEME, null, values);
        }
        db.close();
    }

    public void addPlaces(List<Places.Place> schemes) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLACES);
        for (int i = 0; i < schemes.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(SCH_ID, schemes.get(i).getId());
            values.put(SCHEME_NAME, schemes.get(i).getName());
            values.put(PLACE_TYPE, schemes.get(i).getType());
            values.put(UPPER_NODE_ID, schemes.get(i).getUpper_node_id());
            db.insert(TABLE_PLACES, null, values);
        }
        db.close();
    }

    public void addDepartments(List<Departments.Department> schemes) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DEPARTMENT);
        for (int i = 0; i < schemes.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(SCH_ID, schemes.get(i).getId());
            values.put(SCHEME_NAME, schemes.get(i).getName());
            db.insert(TABLE_DEPARTMENT, null, values);
        }
        db.close();
    }

    public List<Schemes.SchemeData> getSchemes() {
        ArrayList<Schemes.SchemeData> schemeList = new ArrayList<Schemes.SchemeData>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCHEME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                schemeList.add(new Schemes.SchemeData(cursor.getString(0), cursor.getString(1), "", "", "", cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return schemeList;
    }

    public List<Departments.Department> getDepartments() {
        ArrayList<Departments.Department> schemeList = new ArrayList<Departments.Department>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                schemeList.add(new Departments.Department(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return schemeList;
    }

    public List<Places.Place> getPlaces() {
        ArrayList<Places.Place> schemeList = new ArrayList<Places.Place>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLACES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                schemeList.add(new Places.Place(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return schemeList;
    }
}