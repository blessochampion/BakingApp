package com.bakingmobile.bakingapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bakingmobile.bakingapp.R;
import com.bakingmobile.bakingapp.models.Step;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeStepFragment extends Fragment {

    public static final String KEY_STEP = "step";
    Step mStep;
    TextView mFullDescription;

    public RecipeStepFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        mFullDescription = (TextView) rootView.findViewById(R.id.tv_full_description);
        bindView();
        return  rootView;
    }

    private void bindView() {
        if(mStep != null){
            mFullDescription.setText(mStep.getDescription());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments.containsKey(KEY_STEP)){
            mStep = arguments.getParcelable(KEY_STEP);
        }
    }

    public static RecipeStepFragment getNewInstance(Step step){
        Bundle argument = new Bundle();
        argument.putParcelable(KEY_STEP, step);

        RecipeStepFragment fragment = new RecipeStepFragment();
        fragment.setArguments(argument);

        return  fragment;
    }

}
