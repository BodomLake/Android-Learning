package com.ttit.jetpack.navigation;

import android.app.PendingIntent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ttit.helloworld.R;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 找到控制器，执行具名的action
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        */
        view.findViewById(R.id.button_first).setOnClickListener((v) -> {
            Bundle args = new Bundle();
            // 普通的传值
            args.putString("string-args", "my args here");
            args.putInt("int-args", 100);
            // Bundle b1 = new FirstFragmentArgs.Builder().setAge(100).setUserName("Jack Ma").build().toBundle();
            FirstFragmentArgs arguments = FirstFragmentArgs.fromBundle(getArguments());
            // arguments.getAge();arguments.getUserName();
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_FirstFragment_to_SecondFragment, args);
        });
    }

    private PendingIntent getPendingIntent() {
        Bundle args = new Bundle();
        args.putInt("age", 100);
        return Navigation.findNavController(getActivity(), R.id.button_first)
                .createDeepLink()
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.SecondFragment)
                .setArguments(args)
                .createPendingIntent();
    }
}