package com.example.deross.ui.rigstration.sign_up

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.sign_up_fragment.*
import java.util.concurrent.TimeUnit

class SignUpViewModel : ViewModel() {


     fun startPhoneNumberVerification(phoneNumber: String ,
                                            callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks, activity: Activity) {

        activity?.let {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+2$phoneNumber" ,
                60,
                TimeUnit.SECONDS,
                it,
                callbacks)
        };


    }


}
