package com.example.loginextractdetail.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.loginextractdetail.GlideApp
import com.example.loginextractdetail.R
import com.example.loginextractdetail.databinding.FragmentDetailBinding
import com.example.loginextractdetail.extensions.load

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private var binding: FragmentDetailBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            tvLojaNome.text = args.installment.detail.store

            tvTotalValue.text = args.installment.detail.original_value

            tvTotalLeftValue.text = args.installment.detail.value_diff

            tvParcelaValue.text = args.installment.installment

            val overDueDays = args.installment.detail.overdue_days

            tvAtrasadas.text = getString(R.string.dias_atrasados, overDueDays)

            tvItemTitle.text = args.installment.detail.name

            itemDescription.text = args.installment.detail.description

            var url1 = args.installment.detail.images?.firstOrNull()
            url1 = url1?.substring(0, 4) + "s" + url1?.substring(4, url1.length)

            var url2 = args.installment.detail.images?.lastOrNull()
            url2 = url2?.substring(0, 4) + "s" + url2?.substring(4, url2.length)

            ivItem.load(url1)
            if (url2 != "https://icoded.com.br/api/images/" && url1 == "https://icoded.com.br/api/images/") {
                ivItem.load(url2)
            } else if (url1 == "https://icoded.com.br/api/images/" && url2 == "https://icoded.com.br/api/images/") {

                GlideApp.with(this@DetailFragment).load(R.drawable.ic_launcher_background).into(ivItem)
            }

        }
    }
}