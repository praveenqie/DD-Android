package com.example.developerdiary.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.developerdiary.R;
import com.example.developerdiary.databinding.FragmentHomeBinding;
import com.example.developerdiary.ui.adapter.ListGridAdapter;
import com.example.developerdiary.ui.dto.Tutorial;
import com.example.developerdiary.ui.service.HomeService;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeService.view {

    private FragmentHomeBinding binding;

    private HomeService.presenter presenter;

    private ProgressBar progressBar;
    ArrayList<Tutorial> tutorialList
            = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        fetchAllTutorials();

        Tutorial tutorial = new Tutorial("java","JAVA OPPS");
        Tutorial tutorial1 = new Tutorial("2","2 OPPS");
        Tutorial tutorial3 = new Tutorial("3","2 OPPS");
        Tutorial tutorial4 = new Tutorial("4","2 OPPS");
        Tutorial tutorial5 = new Tutorial("5","2 OPPS");
        Tutorial tutorial6 = new Tutorial("6","2 OPPS");
        Tutorial tutorial7 = new Tutorial("7","2 OPPS");
        Tutorial tutorial8 = new Tutorial("8","2 OPPS");
        tutorialList.add(tutorial);
        tutorialList.add(tutorial1);
        tutorialList.add(tutorial3);
        tutorialList.add(tutorial4);
        tutorialList.add(tutorial5);
        tutorialList.add(tutorial6);
        tutorialList.add(tutorial7);
        tutorialList.add(tutorial8);
        // Assign employeelist to ItemAdapter
        ListGridAdapter itemAdapter = new ListGridAdapter(tutorialList);
        // Set the LayoutManager that
        // this RecyclerView will use.
        RecyclerView recyclerView
                = root.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(itemAdapter);

        return root;
    }

    private void fetchAllTutorials() {
        presenter = new HomePresenter(this);
        presenter.FetchAllTutorials();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void SuccessFetchAllTutorials(List<Tutorial> tutorialList) {
        tutorialList = tutorialList;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
//        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void apiError(String errorMessage) {

    }

}