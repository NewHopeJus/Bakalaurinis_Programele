package com.mastercoding.bakalaurinis.view.questions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.databinding.FragmentOpenQuestionBinding;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitRequest;
import com.mastercoding.bakalaurinis.dtos.AnswerSubmitResponse;
import com.mastercoding.bakalaurinis.model.Question;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModel;
import com.mastercoding.bakalaurinis.viewmodel.QuestionViewModelFactory;

import java.io.File;
import java.io.IOException;


public class OpenQuestionFragment extends Fragment {

    private FragmentOpenQuestionBinding fragmentOpenQuestionBinding;
    private Question question;
    private String levelName;
    private String topicName;
    private QuestionViewModel questionViewModel;
    private StorageReference storageReference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentOpenQuestionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_open_question, container, false);
        levelName = getActivity().getIntent().getStringExtra("levelName");
        topicName = getActivity().getIntent().getStringExtra("topicName");
        MineSecurityManager securityManager = new MineSecurityManager(requireContext());
        questionViewModel = new ViewModelProvider(getActivity(), new QuestionViewModelFactory(levelName, topicName, securityManager)).get(QuestionViewModel.class);
        return fragmentOpenQuestionBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        TextView descriptionTextView = fragmentOpenQuestionBinding.textViewOpenQuestionDescription;

        if (args != null) {
            question = args.getParcelable("questionObject");

            if (question != null) {

                String description = question.getDescription();
                descriptionTextView.setText(description);
                if (description != null && description.length() > 50) {
                    descriptionTextView.setTextSize(16);
                }

            }
        }

        Button buttonSubmit = fragmentOpenQuestionBinding.buttonSubmitOpenQuestion;
        EditText editTextAnswer = fragmentOpenQuestionBinding.editTextOpenQuestion;
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = editTextAnswer.getText().toString();
                if (answer.isEmpty()) {
                    Toast.makeText(getContext(), "Įveskite atsakymą.", Toast.LENGTH_SHORT).show();

                } else {
                    AnswerSubmitRequest answerSubmitRequest = new AnswerSubmitRequest(question.getId(),
                            answer, 0l);

                    questionViewModel.submitAnswer(answerSubmitRequest);
                    questionViewModel.setAnswered(true);
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

                    questionViewModel.getAnswerSubmitResponseLiveData().observe(requireActivity(), new Observer<AnswerSubmitResponse>() {
                        @Override
                        public void onChanged(AnswerSubmitResponse answerSubmitResponse) {
                            fragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container_question_fragment,
                                            FragmentLoadingService.loadAnswerFragment(answerSubmitResponse, args))
                                    .commit();
                        }
                    });
                }

            }
        });

        Button buttonSkip = fragmentOpenQuestionBinding.buttonSkipOpenQuestion;
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionViewModel.getQuestion();
            }
        });


        String imageName = question.getImagePath()!=null? question.getImagePath():"1-lygis/atimtis/1L_1T_1K.png";
        storageReference = FirebaseStorage.getInstance().getReference("images/" + imageName + ".png");

        try {
            File localFile = File.createTempFile("tempfile", ".png");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            fragmentOpenQuestionBinding.imageViewDisplayImage.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Nepavyko užkrauti paveiksliuko", Toast.LENGTH_SHORT).show();
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}