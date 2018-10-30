package com.example.sebas.urano;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

public class EditTextTableDataAdapter extends TableDataAdapter {

    public EditTextTableDataAdapter(Context context, EditText[][] data) {
        super(context, data);
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        return new EditText(getContext());
    }
}