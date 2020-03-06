package com.example.deross.ui.rigstration.sign_up

import android.content.ComponentCallbacks
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.deross.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.sign_up_fragment.*
import java.util.concurrent.TimeUnit

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var auth: FirebaseAuth

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.sign_up_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        btn_Register.setOnClickListener(View.OnClickListener {

            prog_sign_up.visibility = View.VISIBLE
            activity?.let { it1 -> viewModel.startPhoneNumberVerification(ID_Phone.text.toString().trimEnd(),callbacks, it1)
            }



        })



        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                prog_sign_up.visibility = View.GONE


                if (e is FirebaseAuthInvalidCredentialsException) {

                    Snackbar.make(activity!!.findViewById(android.R.id.content), "Quota exceeded.${e.message}",
                        Snackbar.LENGTH_LONG).show()


                } else if (e is FirebaseTooManyRequestsException) {

                    Snackbar.make(activity!!.findViewById(android.R.id.content), "Quota exceeded.${e.message}",
                        Snackbar.LENGTH_LONG).show()

                }

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                prog_sign_up.visibility = View.GONE

                storedVerificationId = verificationId

                Snackbar.make(activity!!.findViewById(android.R.id.content), verificationId,
                    Snackbar.LENGTH_SHORT).show()

                val bundle  = Bundle()
                bundle.putString("code",verificationId)
                bundle.putString("name",ID_Name.text.toString())
                var fragment = VerificationCodeFragment()

                fragment.arguments = bundle
//
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, fragment)
                    ?.commitNow()


            }
        }




    }



}


