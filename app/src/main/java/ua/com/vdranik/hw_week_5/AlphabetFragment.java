package ua.com.vdranik.hw_week_5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ua.com.vdranik.hw_week_5.adapter.LettersAdapter;
import ua.com.vdranik.hw_week_5.entity.Letter;

public class AlphabetFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Letter> letters;
    private RecyclerView list;
    private Button delete;
    private Button add;
    private LettersAdapter adapter;
    private Toolbar toolbar;

    public AlphabetFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alphabet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        delete = (Button) view.findViewById(R.id.delete);
        add = (Button) view.findViewById(R.id.add);
        list = (RecyclerView) view.findViewById(R.id.list);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (3 - position % 3);
            }
        });
        list.setLayoutManager(manager);
        list.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        letters = new ArrayList<>();
        List<String> alphabet = new ArrayList<>();

        //i know, it must be in xml <list>,
        alphabet.add("A");
        alphabet.add("B");
        alphabet.add("C");
        alphabet.add("D");
        alphabet.add("F");
        alphabet.add("G");
        alphabet.add("H");
        alphabet.add("I");
        alphabet.add("J");
        alphabet.add("K");
        alphabet.add("L");
        alphabet.add("M");
        alphabet.add("N");
        alphabet.add("O");
        alphabet.add("P");
        alphabet.add("Q");
        alphabet.add("R");
        alphabet.add("S");
        alphabet.add("T");
        alphabet.add("U");
        alphabet.add("V");
        alphabet.add("W");
        alphabet.add("X");
        alphabet.add("Y");
        alphabet.add("Z");

        for (String let : alphabet) {

            Letter letterEntity = new Letter();
            letterEntity.setLettersLine(let);

            this.letters.add(letterEntity);
        }

        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        adapter = new LettersAdapter(this.letters, toolbar);
        list.setAdapter(adapter);

        delete.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete:
                adapter.deleteCheckedItems();
                toolbar.setTitle(MainActivity.TOOLBAR_PART_NAME + MainActivity.START_POSITION);
                break;
            case R.id.add:
                Letter letter = new Letter();
                letter.setLettersLine("NEW");
                letters.add(letter);
                adapter.notifyData(letters);
                break;
        }
    }
}
