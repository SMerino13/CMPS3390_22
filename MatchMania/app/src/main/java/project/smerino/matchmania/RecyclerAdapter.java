package project.smerino.matchmania;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private List<Scores> scores;
    private Context context;

    public RecyclerAdapter(Context context, List<Scores> scores) {
        this.scores = scores;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtItem.setText(scores.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtItem;
        public ViewHolder(View itemView){
            super(itemView);
            txtItem = itemView.findViewById(R.id.txtItemView);
        }

    }
}