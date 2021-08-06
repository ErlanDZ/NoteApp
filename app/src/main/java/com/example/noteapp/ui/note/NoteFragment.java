package com.example.noteapp.ui.note;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentNoteBinding;
import com.example.noteapp.room.App;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NoteFragment extends Fragment {

    NoteModel noteModel;
    NoteModel mod;
    private FragmentNoteBinding binding;
    public  static final Integer RecordAudioRequestCode =1;
    private SpeechRecognizer speechRecognizer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        setClick(navController);
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermision();
        }
        setDate();
        intentSpeech();
        back();
        radioGroup();
        return root;
    }

    private void radioGroup() {
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_black:

                        break;
                }
            }
        });
    }

    private void back() {
        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigateUp();

            }
        });
    }

    private void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String data =  format.format(new Date(System.currentTimeMillis()));
        binding.txtTime.setText(data);

        format = new SimpleDateFormat("dd LLL");
        String date = format.format(new Date(System.currentTimeMillis()));
        binding.txtData.setText(date);

    }

    private void intentSpeech() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireActivity());
        final Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {
                binding.etNote.setText("");
                binding.etNote.setHint("   Прослушивание...");
            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                binding.micOff.setImageResource(R.drawable.mic_off);
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                binding.etNote.setText(data.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        binding.micOff.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    speechRecognizer.stopListening();
                    binding.micOff.setImageResource(R.drawable.mic);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    speechRecognizer.startListening(speechIntent);
                    binding.micOff.setImageResource(R.drawable.mic);
                }
                return false;
            }
        });
    }

    private void checkPermision() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            ActivityCompat.requestPermissions(requireActivity(), new String[]{
                    Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDate();
    }
    private void getDate() {
        if (getArguments() != null){
            mod = (NoteModel) getArguments().getSerializable("mod");
            if (mod != null){
                binding.etNote.setText(mod.getTitle());
            }
        }
    }

    private void setClick(NavController navController) {
        binding.txtReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.etNote.getText().toString();
                SimpleDateFormat format = new SimpleDateFormat("dd-LL-yyyy HH:mm");
                String data =  format.format(new Date(System.currentTimeMillis()));
                if (mod == null){
                    noteModel = new NoteModel(title, data);
                    App.getInstance().dao().insertTask(noteModel);
                }else {
                    mod.setTitle(title);
                    App.getInstance().dao().update(mod);
                }
                navController.navigateUp();
            }
        });
        binding.etNote.setText("");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(getActivity(),"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        speechRecognizer.destroy();
    }
}