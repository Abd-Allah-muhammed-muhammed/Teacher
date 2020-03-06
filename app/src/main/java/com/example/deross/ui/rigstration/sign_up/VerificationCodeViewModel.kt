package com.example.deross.ui.rigstration.sign_up

import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.deross.databinding.VerificationCodeFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerificationCodeViewModel : ViewModel() {


     fun verifyPhoneNumberWithCode(
         verificationId: String?,
         code: String,
         it: FragmentActivity?,
         binding: VerificationCodeFragmentBinding,
         auth: FirebaseAuth
     ) {
        // [START verify_with_code]

        val credential = PhoneAuthProvider.getCredential(verificationId!!, code )

        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential,binding, it,auth)
    }



    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        binding: VerificationCodeFragmentBinding,
        it: FragmentActivity?,
        auth: FirebaseAuth
    ) {
        binding.progVrifi.visibility = View.VISIBLE
        auth.signInWithCredential(credential)
            .addOnCompleteListener(it!!) { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user

                    val uid = user?.uid
                    binding.progVrifi.visibility = View.GONE

                    binding.layoutChick.visibility = View.GONE
                    binding.layoutFinish.visibility = View.VISIBLE


                } else {
                    binding.verificationCodeEt.error = task.exception?.message
                    binding.progVrifi.visibility = View.GONE

//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        // [START_EXCLUDE silent]
                        binding.verificationCodeEt.error = "Invalid code."
                        // [END_EXCLUDE]
                    }

                }
            }
    }
}
