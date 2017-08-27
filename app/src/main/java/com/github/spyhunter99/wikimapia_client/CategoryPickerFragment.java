package com.github.spyhunter99.wikimapia_client;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.wikimapia.api.Categories;
import org.wikimapia.api.Category;
import org.wikimapia.api.WikimapiaAPI;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryPickerFragment extends Fragment implements WikimapiaAPI.OnCategoryResult {

    List<Category> list = new ArrayList<>();
    EditText lv;
    EditText inputSearch;
    CategoryAdapter adapter;
    WikimapiaAPI api;

    public CategoryPickerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_blank, container, false);
        // Inflate the layout for this fragment
        ListView lv = (ListView) root.findViewById(R.id.categoryListview);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        EditText inputSearch = (EditText) root.findViewById(R.id.listfilter);

        // Adding items to listview
        adapter = new CategoryAdapter (this.getContext());
        lv.setAdapter(adapter);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                CategoryPickerFragment.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }

            @Override
            public void afterTextChanged(Editable arg0) {}
        });

        api = new WikimapiaAPI("97ADFBFD-3EDAEC5C-0D2D11EF-A917D83F-E694A7B8-3BA3D1FD-E9F8D9CC-446AA013");

            api.getAllCategories(this);
        return root;
    }

    @Override
    public void onCategoryResult(Categories results) {
        adapter.addData(results);
    }
}
