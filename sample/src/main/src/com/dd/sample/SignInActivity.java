package com.dd.sample;

import com.dd.processbutton.iml.ActionProcessButton;
import com.dd.sample.utils.ProgressGenerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends Activity implements
		ProgressGenerator.OnCompleteListener {

	public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
	private ActionProcessButton btnSignIn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_sign_in);

		final EditText editEmail = (EditText) findViewById(R.id.editEmail);
		final EditText editPassword = (EditText) findViewById(R.id.editPassword);
		final CheckBox cbErrorStatus = (CheckBox) findViewById(R.id.cbErrorStatus);

		final ProgressGenerator progressGenerator = new ProgressGenerator(this);
		cbErrorStatus.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				progressGenerator.setError(isChecked);				
			}
		});
		btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);

		Bundle extras = getIntent().getExtras();
		if (extras != null && extras.getBoolean(EXTRAS_ENDLESS_MODE)) {
			btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
		} else {
			btnSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
		}
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progressGenerator.start(btnSignIn);
				btnSignIn.setEnabled(false);
				editEmail.setEnabled(false);
				editPassword.setEnabled(false);
			}
		});
	}

	@Override
	public void onComplete() {
		Toast.makeText(this, R.string.Loading_Complete, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onError() {
		btnSignIn.setErrorStatus(true);

	}

}
