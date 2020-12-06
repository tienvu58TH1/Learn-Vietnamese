package com.example.projectda.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.projectda.R;
import com.example.projectda.callback.CallbackQuestion;
import com.example.projectda.models.Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SpeechToTextAdapter extends RecyclerView.Adapter<SpeechToTextAdapter.ViewHolder> {
    private ArrayList<Question> questionsSTT;
    private CallbackQuestion callback;
    private TableLayout.LayoutParams params;
    private Context context;
    private int levelarrived;

    public SpeechToTextAdapter(ArrayList<Question> questionsSTT, CallbackQuestion callback, Context context, int column, int levelarrived){
        this.levelarrived=levelarrived;
        this.questionsSTT=questionsSTT;
        this.callback=callback;
        this.context=context;

        params=new TableLayout.LayoutParams(getDeviceWidth(context)/column,getDeviceWidth(context)/column);

    }

    public void updateTrackAdapter(ArrayList<Question> questionsSTT){
        this.questionsSTT=questionsSTT;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.item_question_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        View view= holder.getView();
        TextView tvQuestion=view.findViewById(R.id.tvQuestion);
        Question question=questionsSTT.get(position);
        CardView cardView=view.findViewById(R.id.cvQuestion);
        tvQuestion.setText(question.getLevel()+"");
        if (question.getLevel() > levelarrived){
            tvQuestion.setBackgroundResource(R.color.colorGray);
            tvQuestion.setEnabled(false);
            cardView.setCardElevation(0);
        }else {
            tvQuestion.setBackgroundResource(R.color.colorMagenta);
            tvQuestion.setEnabled(true);
            cardView.setCardElevation(8);
        }
        tvQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onItemClick(position);
            }
        });
        view.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return questionsSTT.size();
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
