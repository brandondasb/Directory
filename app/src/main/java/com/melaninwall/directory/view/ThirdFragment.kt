package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melaninwall.directory.R


class ThirdFragment : Fragment() {
    companion object {

        fun create(): ThirdFragment {
            val bundle = Bundle()
            val fragment = ThirdFragment()
            fragment.arguments = bundle
            fragment

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.third_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

}