package com.example.deross.ui.rigstration.ui.rigstrationfragmentcontainer

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.deross.R
import com.example.deross.databinding.RigstrationFragmentContainerFragmentBinding
import com.example.deross.ui.rigstration.sign_up.SignUpFragment
import kotlinx.android.synthetic.main.rigstration_fragment_container_fragment.*

class RigstrationFragmentContainer : Fragment() {

    companion object {
        fun newInstance() = RigstrationFragmentContainer()
    }

    private lateinit var viewModel: RigstrationFragmentContainerViewModel

   private lateinit var binding :RigstrationFragmentContainerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater,R.layout.rigstration_fragment_container_fragment,container,false)
       return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this).get(RigstrationFragmentContainerViewModel::class.java)


        binding.createAccount.setOnClickListener(View.OnClickListener {

            activity?.supportFragmentManager?.beginTransaction()
    ?.replace(R.id.container, SignUpFragment.newInstance())
    ?.commitNow()
        })



    }


//
}
