package com.example.deross.ui.rigstration.sign_up

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.deross.R
import com.example.deross.databinding.VerificationCodeFragmentBinding
import com.example.deross.ui.hom.HomActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class VerificationCodeViewModel : ViewModel() {

    private var repass = ""
    private var pass = ""

    fun verifyPhoneNumberWithCode(
        verificationId: String?,
        code: String,
        it: FragmentActivity?,
        binding: VerificationCodeFragmentBinding,
        auth: FirebaseAuth,
        name: String?,
        number: String?
    ) {
        // [START verify_with_code]

        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)

        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential, binding, it, auth, name, number)
    }


    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        binding: VerificationCodeFragmentBinding,
        it: FragmentActivity?,
        auth: FirebaseAuth,
        name: String?,
        number: String?
    ) {
        binding.progVrifi.visibility = View.VISIBLE
        auth.signInWithCredential(credential)
            .addOnCompleteListener(it!!) { task ->
                if (task.isSuccessful) {

                    val user = task.result?.user

                    val token = user?.uid
                    binding.progVrifi.visibility = View.GONE

                    binding.layoutChick.visibility = View.GONE
                    binding.layoutFinish.visibility = View.VISIBLE


                    getCountries(binding, it)


                } else {

                    Snackbar.make(
                        it.findViewById(android.R.id.content), task.exception?.message.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()

                    binding.progVrifi.visibility = View.GONE

//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        // [START_EXCLUDE silent]
                        Snackbar.make(
                            it.findViewById(android.R.id.content), "Invalid code",
                            Snackbar.LENGTH_LONG
                        ).show()


                        // [END_EXCLUDE]
                    }

                }
            }
    }

    private fun getCountries(binding: VerificationCodeFragmentBinding, it: FragmentActivity) {


        val countries = listOf("cairo", "el mansoura", "aga")


        val adapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(
            binding.root.context, R.layout.item_spinner, countries
        )


        adapter.setDropDownViewResource(R.layout.drop_dowen_spenner)
        binding.spCountry.adapter = adapter
        binding.spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


                val country = parent?.getItemAtPosition(position).toString()

                getCitiesbyId(binding, it)

                Snackbar.make(
                    it.findViewById(android.R.id.content), country,
                    Snackbar.LENGTH_LONG
                ).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    private fun getCitiesbyId(
        binding: VerificationCodeFragmentBinding,
        it: FragmentActivity
    ) {


        val cities = listOf("meit samanoud", " sonpokht")
        val adapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(
            binding.root.context, R.layout.item_spinner, cities
        )


        adapter.setDropDownViewResource(R.layout.drop_dowen_spenner)
        binding.apCity.adapter = adapter
        binding.apCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val city = parent?.getItemAtPosition(position).toString()
                Snackbar.make(
                    it.findViewById(android.R.id.content), city,
                    Snackbar.LENGTH_LONG
                ).show()
            }


        }
    }


    public fun sigUp(v: View) {


        if (pass == "") {

            Snackbar.make(
                v.rootView.findViewById(android.R.id.content), "please enter your password",
                Snackbar.LENGTH_LONG
            ).show()
        } else if (repass == "") {


            Snackbar.make(
                v.rootView.findViewById(android.R.id.content), "please enter your re password",
                Snackbar.LENGTH_LONG
            ).show()

        } else if (!pass.equals(repass)) {


            Snackbar.make(
                v.rootView.findViewById(android.R.id.content), "password does not match",
                Snackbar.LENGTH_LONG
            ).show()


        } else {

            val intent = Intent(v.context, HomActivity::class.java)

            v.context.startActivity(intent)
        }


    }


    fun passwordChanging(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {

        pass = s.toString()

    }



    fun rePasswordChanging(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {

        repass = s.toString()

    }

}
