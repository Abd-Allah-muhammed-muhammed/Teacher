package com.example.deross.ui.rigstration.sign_up

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.deross.R
import com.example.deross.databinding.SignUpFragmentBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.sign_up_fragment.*

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var auth: FirebaseAuth
    private var binding :SignUpFragmentBinding ? = null

    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
binding = DataBindingUtil.inflate(inflater,R.layout.sign_up_fragment,container,false)


        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SignUpViewModel::class.java)
        auth = FirebaseAuth.getInstance()
       binding?.btnRegister?.setOnClickListener(View.OnClickListener {

            if (TextUtils.isEmpty(binding?.IDName?.text)){

                Snackbar.make(activity!!.findViewById(android.R.id.content), "Please Enter Your Name",
                    Snackbar.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(binding?.IDPhone?.text)){

                Snackbar.make(activity!!.findViewById(android.R.id.content), "Please Enter Your Phone",
                    Snackbar.LENGTH_LONG).show()
            }else {
                binding?.progSignUp?.visibility = View.VISIBLE
                activity?.let { it1 -> viewModel.startPhoneNumberVerification(binding?.IDPhone?.text.toString().trimEnd(),callbacks, it1)
                }
            }




        })



        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                binding?.progSignUp?.visibility = View.GONE


                if (e is FirebaseAuthInvalidCredentialsException) {

                    Snackbar.make(activity!!.findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_LONG).show()


                } else if (e is FirebaseTooManyRequestsException) {

                    Snackbar.make(activity!!.findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_LONG).show()

                }

            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                binding?.progSignUp?.visibility = View.GONE

                storedVerificationId = verificationId



                val bundle  = Bundle()
                bundle.putString("code",verificationId)
                bundle.putString("name",binding?.IDName?.text.toString())
                bundle.putString("number",binding?.IDPhone?.text.toString())
                var fragment = VerificationCodeFragment()

                fragment.arguments = bundle
//
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, fragment)
                    ?.addToBackStack("verif")
                    ?.commit()


            }
        }




    }






}


