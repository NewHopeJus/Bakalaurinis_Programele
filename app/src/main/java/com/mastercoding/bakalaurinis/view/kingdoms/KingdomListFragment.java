package com.mastercoding.bakalaurinis.view.kingdoms;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.KingdomDto;
import com.mastercoding.bakalaurinis.dtos.KingdomListsResponse;
import com.mastercoding.bakalaurinis.model.Kingdom;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.view.kingdoms.views.ElfuKaralysteFragment;
import com.mastercoding.bakalaurinis.view.kingdoms.views.GeliuKaralysteFragment;
import com.mastercoding.bakalaurinis.view.kingdoms.views.GrybuKaralysteFragment;
import com.mastercoding.bakalaurinis.view.questions.QuestionActivity;
import com.mastercoding.bakalaurinis.viewmodel.KingdomViewModel;
import com.mastercoding.bakalaurinis.viewmodel.KingdomViewModelFactory;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KingdomListFragment extends Fragment implements CustomAdapter.ItemClickListener {

    private List<KingdomDto> kingdomList = new ArrayList<>();
    private CustomAdapter customAdapter = new CustomAdapter(this);
    private KingdomViewModel kingdomViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kingdom_list, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewFragmentKingdomList);

        kingdomList = new ArrayList<>();


        MineSecurityManager securityManager = new MineSecurityManager(requireContext());

        kingdomViewModel = new ViewModelProvider(this, new KingdomViewModelFactory(securityManager)).get(KingdomViewModel.class);

        if (kingdomViewModel.getKingdomListsResponseLiveData() == null) {
            kingdomViewModel.getKingdoms();
        }


        kingdomViewModel.getKingdomListsResponseLiveData().observe(getViewLifecycleOwner(), new Observer<KingdomListsResponse>() {

            @Override
            public void onChanged(KingdomListsResponse kingdomListsResponse) {
                kingdomList.clear();
                kingdomList.addAll(kingdomListsResponse.getOpenedKingdoms());
                kingdomList.addAll(kingdomListsResponse.getClosedKingdoms());
                customAdapter.updateData(kingdomList);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(customAdapter);


        return rootView;
    }

    @Override
    public void onItemClick(int position) {

        KingdomDto selectedKingdom = kingdomList.get(position);
        Long kingdomId = selectedKingdom.getId();

        FragmentManager manager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        Fragment fragment;

        Bundle bundle = new Bundle();
        bundle.putLong("kingdomId", kingdomId);


        switch (selectedKingdom.getName()) {
            case "Gėlių karalystė":
                fragment = new GeliuKaralysteFragment();
                break;
            case "Elfų karalystė":
                fragment = new ElfuKaralysteFragment();
                break;
            case "Grybų karalystė":
                fragment = new GrybuKaralysteFragment();
                break;
            default:
                fragment = new GrybuKaralysteFragment();
                break;

        }
        fragment.setArguments(bundle);

        fragmentTransaction.replace(R.id.fragment_container_kingdom_list, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}