package com.example.deross.ui.rigstration.sign_up

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import com.example.deross.R
import com.example.deross.databinding.VerificationCodeFragmentBinding
import com.google.android.material.snackbar.Snackbar
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


        return binding?.root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(VerificationCodeViewModel::class.java)
        binding?.verifi = viewModel
        auth = FirebaseAuth.getInstance()

        binding?.btnCheck?.setOnClickListener( View.OnClickListener {


            if (TextUtils.isEmpty(binding?.verificationCodeEt?.text)){

                Snackbar.make(activity!!.findViewById(android.R.id.content), "Please Enter  THE 6 WIDGET",
                    Snackbar.LENGTH_LONG).show()

            }else{

                val code = arguments?.getString("code")
                val name = arguments?.getString("name")
                val number = arguments?.getString("number")
                viewModel.verifyPhoneNumberWithCode(code, binding?.verificationCodeEt?.text.toString().trimEnd()
                ,activity, binding!!,auth,name,number
                )

            }
        })


    }









}
