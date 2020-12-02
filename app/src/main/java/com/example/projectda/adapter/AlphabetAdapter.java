package com.example.projectda.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackAlphabet;
import com.example.projectda.callback.CallbackQuestion;
import com.example.projectda.models.Alphabet;
import com.example.projectda.models.Question;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AlphabetAdapter extends RecyclerView.Adapter<AlphabetAdapter.ViewHolder> {
    private ArrayList<Alphabet> mAlphabets;
    private CallbackAlphabet callback;
    private TableLayout.LayoutParams params;
    private Context context;

    public AlphabetAdapter(ArrayList<Alphabet> alphabets, CallbackAlphabet callback, Context context, int column){
        this.mAlphabets=alphabets;
        this.callback=callback;
        this.context=context;

        params=new TableLayout.LayoutParams(getDeviceWidth(context)/column,getDeviceWidth(context)/column);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_alphabet_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        final Alphabet alphabet=mAlphabets.get(position);
        final TextView tvAlphabet=view.findViewById(R.id.tvAlphabet);
        tvAlphabet.setText(alphabet.getText().toUpperCase());
        view.setLayoutParams(params);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAlphabets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
        }

        public View getView() {
            return view;
        }
    }
    public static int getDeviceWidth(Context context) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Point point = new Point();
            wm.getDefaultDisplay().getSize(point);
            return point.x;
        } else {
            return wm.getDefaultDisplay().getWidth();
        }
    }
}
