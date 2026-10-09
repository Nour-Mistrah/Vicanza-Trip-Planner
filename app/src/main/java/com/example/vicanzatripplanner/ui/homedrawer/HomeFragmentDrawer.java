package com.example.vicanzatripplanner.ui.homedrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vicanzatripplanner.databinding.FragmentHomeDrawerBinding;

public class HomeFragmentDrawer extends Fragment {

    private FragmentHomeDrawerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeDrawerViewModel homeDrawerViewModel =
                new ViewModelProvider(this).get(HomeDrawerViewModel.class);

        binding = FragmentHomeDrawerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}