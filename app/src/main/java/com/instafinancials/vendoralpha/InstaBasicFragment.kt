package com.instafinancials.vendoralpha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.instafinancials.vendoralpha.databinding.InstabasicFragmentBinding


class InstaBasicFragment : Fragment() {
    private lateinit var binding: InstabasicFragmentBinding

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.instabasic_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var expand1 = false
        var expand2 = false
        binding.exp1.setOnClickListener {
            if (expand1) {
                expand1 = false
                binding.par1.visibility = View.VISIBLE
                binding.exp1.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
            } else {
                expand1 = true
                binding.par1.visibility = View.GONE
                binding.exp1.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
            }
        }

        binding.exp2.setOnClickListener {
            if (expand2) {
                expand2 = false
                binding.par2.visibility = View.VISIBLE
                binding.exp2.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
            } else {
                expand2 = true
                binding.par2.visibility = View.GONE
                binding.exp2.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
            }
        }
    }

}
