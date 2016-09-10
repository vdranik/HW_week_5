package ua.com.vdranik.hw_week_5.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.com.vdranik.hw_week_5.MainActivity;
import ua.com.vdranik.hw_week_5.R;
import ua.com.vdranik.hw_week_5.entity.Letter;

public class LettersAdapter extends RecyclerView.Adapter<LettersAdapter.ViewHolder> {

    private Toolbar toolbar;
    private ArrayList<Letter> letters;

    public LettersAdapter(List<Letter> letters, Toolbar toolbar) {
        this.letters = new ArrayList<>(letters);
        this.toolbar = toolbar;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alphabet_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(letters.get(position));

        holder.checkbox.setOnCheckedChangeListener(null);
        holder.checkbox.setChecked(letters.get(position).isSelected());

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                letters.get(holder.getAdapterPosition()).setSelected(isChecked);
                toolbar.setTitle(MainActivity.TOOLBAR_PART_NAME + getCheckedLetters().size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return letters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView letterLine;
        private CheckBox checkbox;

        public ViewHolder(View v) {
            super(v);
            letterLine = (TextView) v.findViewById(R.id.letterLine);
            checkbox = (CheckBox) v.findViewById(R.id.checkbox);
        }

        public void bindData(Letter letter) {
            letterLine.setText(letter.getLettersLine());
        }
    }

    public List<Letter> getCheckedLetters() {
        List<Letter> checkedLetters = new ArrayList<>();

        for (Letter l : letters) {
            if (l.isSelected()) {
                checkedLetters.add(l);
            }
        }
        return checkedLetters;
    }

    public void notifyData(ArrayList<Letter> letters) {
        this.letters = letters;
        notifyDataSetChanged();
    }

    public void deleteCheckedItems() {
        for (Letter l : getCheckedLetters()) {
            notifyItemRemoved(letters.indexOf(l));
            letters.remove(l);
        }
        getCheckedLetters().clear();
    }
}
