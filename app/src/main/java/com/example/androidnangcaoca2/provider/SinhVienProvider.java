package com.example.androidnangcaoca2.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.sql.SQLException;
import java.util.HashMap;

public class SinhVienProvider extends ContentProvider {

    static final String PROVIDER_NAME = "sinhvien";
    static final String URL = "content://" + PROVIDER_NAME + "/sinhvien";
   public static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "massv";
    static final String NAME = "ten";
    static final String GRADE = "diemtb";
    static final int SINHVIENS =1;
    static final int SINVIEN_DETAIL = 2;
    private static HashMap<String, String> SINHVIEN_PROJECTION_MAP;
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "sinhviens", SINHVIENS);
        uriMatcher.addURI(PROVIDER_NAME, "sinhviens/#", SINVIEN_DETAIL);
    }
    private SQLiteDatabase db;


    public SinhVienProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (uriMatcher.match(uri)){
            /**
             * Get all student records
             */
            case SINHVIENS:
                return "sinhvien";
            /**
             * Get a particular student
             */
            case SINVIEN_DETAIL:
                return "sinhvien";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            long rowID = db.insert("sinhvien",null, values);
            if(rowID>0) {
                Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
                getContext().getContentResolver().notifyChange(_uri, null);
                return  _uri;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return (db== null) ? false : true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables("sinhvien");
        switch (uriMatcher.match(uri)) {
            case SINHVIENS:
                sqLiteQueryBuilder.setProjectionMap(SINHVIEN_PROJECTION_MAP);
                break;
            case  SINVIEN_DETAIL:
                sqLiteQueryBuilder.appendWhere("massv =" + uri.getPathSegments().get(1));
                break;
            default:
        }
        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection,
                selectionArgs, null, null, null);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return  cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
