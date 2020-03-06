package com.example.deross.ui.rigstration.sign_up

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.deross.R
import com.example.deross.databinding.VerificationCodeFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class VerificationCodeFragment : Fragment() {

    companion object {
        fun newInstance() = VerificationCodeFragment()
    }



    private var  binding:VerificationCodeFragmentBinding ? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: VerificationCodeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    {

        binding = DataBindingUtil.inflate(inflater,R.layout.verification_code_fragment,container,false)

        auth = FirebaseAuth.getInstance()
        return binding?.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VerificationCodeViewModel::class.java)


        binding?.btnCheck?.setOnClickListener( View.OnClickListener {


            if (TextUtils.isEmpty(binding?.verificationCodeEt.toString())){

                binding?.verificationCodeEt?.error  = "ENTER THE 6 WIDGET CODE"
            }else{

                val code = arguments?.getString("code")
                viewModel.verifyPhoneNumberWithCode(code, binding?.verificationCodeEt?.text.toString().trimEnd()
                ,activity, binding!!,auth
                )

            }
        })


    }








}
