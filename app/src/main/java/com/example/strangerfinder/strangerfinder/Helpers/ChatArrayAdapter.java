package com.example.strangerfinder.strangerfinder.Helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.strangerfinder.strangerfinder.Models.Mensaje;
import com.example.strangerfinder.strangerfinder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 13/11/2017.
 */

public class ChatArrayAdapter extends ArrayAdapter<Mensaje> {

    private TextView chatText;
    private List<Mensaje> chatMessageList = new ArrayList<Mensaje>();
    private Context context;

    @Override
    public void add(Mensaje object) {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public Mensaje getItem(int index) {
        return this.chatMessageList.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Mensaje chatMessageObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (chatMessageObj.izquierda) {
            row = inflater.inflate(R.layout.msg_izquierda, parent, false);
        }else{
            row = inflater.inflate(R.layout.msg_derecha, parent, false);
        }
        chatText = (TextView) row.findViewById(R.id.txtMensaje);
        chatText.setText(chatMessageObj.mensaje);
        return row;
    }
}
