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
import com.example.developerdiary.api.ApiUtils;
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
    private ListGridAdapter itemAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ApiUtils.initialize(getContext());
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        fetchAllTutorials();

        // Assign employeelist to ItemAdapter
        itemAdapter = new ListGridAdapter(tutorialList);
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
        this.tutorialList.clear();
        this.tutorialList.addAll(tutorialList);
        itemAdapter.notifyDataSetChanged();
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