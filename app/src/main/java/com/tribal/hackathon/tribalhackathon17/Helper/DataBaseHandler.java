package com.tribal.hackathon.tribalhackathon17.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Department;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.Places;
import com.tribal.hackathon.tribalhackathon17.Schemes.Model.Data.SchemeData;

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
    private String PLACE_NAME_OR_SCHEME_NAME = "name";
    private SQLiteDatabase writableDatabase;
    private String TABLE_SCHEME = "scheme";
    private String SCHEME_NAME = "name";
    private String DEPTNAME = "dept";
    private String SCH_ID = "id";
    private String TABLE_DEPARTMENT = "departments";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_INITIAL_TABLE = "CREATE TABLE " + TABLE_INITIAL + "("
                + UNIQUE_ID + " TEXT ," + PLACE_NAME_OR_SCHEME_NAME + " TEXT)";
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INITIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
        onCreate(db);
    }

    public void addInitialEntries(String id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UNIQUE_ID, id);
        values.put(PLACE_NAME_OR_SCHEME_NAME, name);
        db.insert(TABLE_INITIAL, null, values);
        db.close();
    }

    public void addSchemes(List<SchemeData> schemes) {
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

    public void addPlaces(List<Places> schemes) {
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

    public void addDepartments(List<Department> schemes) {
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

    public List<SchemeData> getSchemes() {
        ArrayList<SchemeData> schemeList = new ArrayList<SchemeData>();
        String selectQuery = "SELECT  * FROM " + TABLE_SCHEME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                schemeList.add(new SchemeData(cursor.getString(0), cursor.getString(1), "", "", "", cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return schemeList;
    }

    public List<Department> getDepartments() {
        ArrayList<Department> schemeList = new ArrayList<Department>();
        String selectQuery = "SELECT  * FROM " + TABLE_DEPARTMENT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                schemeList.add(new Department(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return schemeList;
    }

    public List<Places> getPlaces() {
        ArrayList<Places> schemeList = new ArrayList<Places>();
        String selectQuery = "SELECT  * FROM " + TABLE_PLACES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                schemeList.add(new Places(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return schemeList;
    }
}