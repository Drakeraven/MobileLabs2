package edu.tacoma.uw.stephd27.testinglab;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    OnRegisterListener mRegisterListener;
    private final String TAG = "Register Fragment";
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        Button regBtn = (Button) v.findViewById(R.id.btn_register);
        final EditText emailText = (EditText) v.findViewById(R.id.edit_email);
        final EditText pwdText = (EditText) v.findViewById(R.id.edit_pwd);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String pwd = pwdText.getText().toString();

                try {
                    Account acc = new Account(email, pwd);
                    mRegisterListener.register(acc);
                } catch (Exception e) {
                    Toast.makeText(v.getContext(),
                            "Unable to register: " + e.getMessage()
                    , Toast.LENGTH_LONG).show();
                }
            }
        });
        TextView loginTextView = (TextView) v.findViewById(R.id.tv_login);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegisterListener.launchLoginFragment();
            }
        });
        return  v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterListener) {
            mRegisterListener = (OnRegisterListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CourseAddListener");
        }
    }

    public interface OnRegisterListener {
        void register(Account account);
        void launchLoginFragment();
    }

}
