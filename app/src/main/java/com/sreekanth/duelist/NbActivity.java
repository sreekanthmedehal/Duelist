package com.sreekanth.duelist;

import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class NbActivity extends ExpandableListActivity {

final private String[] asColumnsToReturn = {
   NBData.NB_POLNO,
    NBData.NB_COMMDT,
    NBData.NB_PREM };

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.browse);

DataViewerSQLiteHelper stockSQLHelper = new DataViewerSQLiteHelper(this);
SQLiteDatabase database = stockSQLHelper.getWritableDatabase(); 




SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
queryBuilder.setTables(NBData.TABLE_STOCK);
ExpandableListView browseView = (ExpandableListView)findViewById(android.R.id.list);

Cursor mCursor = queryBuilder.query(database, asColumnsToReturn, null, null,
        null, null," nbpolno DESC");
startManagingCursor(mCursor);


//SimpleCursorTreeAdapter mAdapter = new SimpleCursorTreeAdapter(this,
  //      mCursor, R.layout.row, R.layout.exprow,
  //      new String[] { Items.ITEMS_ITEM }, new int[] { R.id.txtItem },
  //      R.layout.exprow, R.layout.exprow, new String[] { Items.ITEMS_DESC },
  //      new int[] { R.id.dscItem }) {

  //  @Override
  //  protected Cursor getChildrenCursor(Cursor groupCursor) {


    //    return groupCursor;
   // }
};

//browseView.setAdapter(mAdapter);


}

//}

